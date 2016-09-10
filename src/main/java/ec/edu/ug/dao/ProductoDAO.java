package ec.edu.ug.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.edu.ug.model.Producto;
import ec.edu.ug.util.PaginationTemplate;

@Stateless(mappedName="productoDAO")
public class ProductoDAO extends AbstractDAO<Producto,Long> {

	@PersistenceContext
	private EntityManager em;
	
	public ProductoDAO() {
		super(Producto.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	public List<Producto> listProductos(Producto filter,PaginationTemplate pagination){
		CriteriaQuery<Producto> cq=createCriteriaQuery();
		if(filter!=null){
			CriteriaBuilder cb=getCriteriaBuilder();
			Root<Producto> root=cq.from(Producto.class);
			if(filter.getId()!=null)
				cq.where(cb.equal(root.<Long>get("id"),filter.getId()));
			if(filter.getCodigo()!=null)
				cq.where(cb.like(root.<String>get("codigo"),filter.getCodigo()+"%"));
			if(filter.getDescripcion()!=null)
				cq.where(cb.like(root.<String>get("descripcion"),filter.getDescripcion()+"%"));
		}
		return findByCriteriaQuery(cq, pagination);
	}
	

}

