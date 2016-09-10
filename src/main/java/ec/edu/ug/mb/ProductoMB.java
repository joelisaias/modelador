package ec.edu.ug.mb;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ec.edu.ug.dao.ProductoDAO;
import ec.edu.ug.model.Producto;
import lombok.Getter;
import lombok.Setter;

@Named("productoMB")
@SessionScoped
public class ProductoMB extends TemplateMB {


	private static final long serialVersionUID = 1L;
	
	@EJB(mappedName="productoDAO")
	private ProductoDAO dao;
	
	@Setter	private List<Producto> productos;

	@Getter @Setter private Producto currentDTO;
	@Getter @Setter private Producto filterDTO;

	private static final String INDEX_URL	= "index.jsf";
	private static final String ADD_URL		= "edit.jsf";
	private static final String EDIT_URL	= "edit.jsf";
	
	@PostConstruct
	public void init() {
		filterDTO=new Producto();
	}
	
	public List<Producto> getProductos() {
		productos=dao.listProductos(null, null);
		return productos;
	}
	
	public String buscar(){
		return INDEX_URL;
	}
	
	public String agregar(){
		currentDTO=new Producto();
		currentDTO.setFechacreacion(Calendar.getInstance().getTime());
		currentDTO.setEstado("A");
		return ADD_URL;
	}
	
	public String eliminar(Long id){
		currentDTO=dao.find(id);
		dao.remove(currentDTO);
		return INDEX_URL;
	}
	
	public String eliminar(){
		dao.remove(currentDTO);
		return INDEX_URL;
	}
	
	public String editar(Long id){
		currentDTO=dao.find(id);
		currentDTO.setFechamodificacion(Calendar.getInstance().getTime());
		return EDIT_URL;
	}
	public String guardar(){
		dao.save(currentDTO);
		return INDEX_URL;
	}
	
	public String volver(){		
		return INDEX_URL;
	}

}
