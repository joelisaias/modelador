package ec.edu.ug.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.primefaces.model.LazyDataModel;

import ec.edu.ug.model.AbstractEntity;
import ec.edu.ug.util.DataTableModel;
import ec.edu.ug.util.PaginationTemplate;
import lombok.Getter;
import lombok.Setter;

public abstract class AbstractMB <DTO extends AbstractEntity> extends TemplateMB {

	private static final long serialVersionUID = 9127180840504035585L;
	

	@Getter @Setter protected boolean habilitaCrear;
	@Getter @Setter protected boolean habilitaEditar;
	@Getter @Setter protected boolean habilitaGuardar;
	@Getter @Setter protected boolean habilitaEliminar;
	@Getter @Setter protected boolean habilitaSolicitar;
	@Getter @Setter protected boolean habilitaAprobar;
	@Getter @Setter protected boolean habilitaRechazar;
	@Getter @Setter protected boolean habilitaRecuperar;
	@Getter @Setter protected boolean habilitaReversar;
	@Getter @Setter protected boolean habilitaReportar;
	@Getter @Setter protected boolean habilitaGenerar;
	@Getter @Setter protected boolean preLoaded;
	@Getter @Setter protected Number searchId;
	@Getter @Setter protected DTO currentDTO;
	
	protected DTO searchDTO;
	protected PaginationTemplate pagination;
	protected LazyDataModel<DTO> dataModel=new DataTableModel<DTO>() {

		private static final long serialVersionUID = -6937043607542982666L;
		
		public DTO defineFilter() {
			return getSearchDTO();
		};
		
		@Override
		public List<DTO> loadData(DTO dto, PaginationTemplate pagination) {
			if(!isPreLoaded())
				return new ArrayList<DTO>();
			return loadMainResult(dto, pagination);
		};

		@Override
		public DTO rowSelect(Long id) {			
			return actionEdit(id);
		};	
		
		
	};
		private List<DTO> searchResult; 
	
	/**
	 * Instanciar serachDTO & currentDTO
	 * <br/>
	 * Ejemplo:
	 * <br/> 
	 * <code>
	 *  searchDTO=new DTO();</br>
	 * </code>
	 * 
	 * Entiendase por DTO al tipo de dato con el que construyen su MB <br/>	  
	 * Ejemplo:
	 * <br/>
	 * <code>
	 * public class OpcionMB extends SeguridadMB&lt;ModuloDTO>{...<br/>
	 * public abstract class SeguridadMB&lt;DTO extends GenericSeguridadDTO&lt;DTO>> extends DTOTemplateMB&lt;DTO> {...<br/>
	 * </code>
	 * la implementacion seria <br/>
	 * <code>
	 * public void instanceSearchDTO() {
	 *	searchDTO  = new ModuloDTO();		
	 * }
	 * </code>
	 * 
	 * 
	 * @author <a href="mailto:joyalt77@gmail.com">Joel Alvarado</a>
	 * @since 2015-09-15  v 1.0
	 */
	public abstract DTO instanceSearchDTO();


	
	@PostConstruct
	protected void initialize(){
		checkPermision();
		init();
	}
	
	public abstract void init();
	public abstract void enableControls();
	
	/**
	 * TODO agregar comentario
	 * 
	 * @author Joel Alvarado
	 * @return
	 */
	public abstract List<DTO> loadMainResult(DTO filter,PaginationTemplate pagination);
	public abstract DTO 	  actionEdit(Number id);
	public abstract DTO 	  actionNew();
	public abstract void 	  actionDelete(DTO dto);
	public abstract DTO 	  actionSave(DTO dto);
	public abstract void 	  onRedirect();
	public void actionAprobar(DTO dto){
		throw new UnsupportedOperationException("Accion no permitida");
	}
	public void actionRechazar(DTO dto){
		throw new UnsupportedOperationException("Accion no permitida");
	}
	public void actionSolicitarAprobacion(DTO dto){
		throw new UnsupportedOperationException("Accion no permitida");
	}
	public void actionRecuperar(DTO dto){
		throw new UnsupportedOperationException("Accion no permitida");
	}
	public void actionReversar(DTO dto){
		throw new UnsupportedOperationException("Accion no permitida");
	}
	
	public void actionSearch(ActionEvent evt){
			dataModel=new DataTableModel<DTO>() {
	
				private static final long serialVersionUID = -6937043607542982666L;
				
				public DTO defineFilter() {
					return searchDTO;
				};
				
				@Override
				public List<DTO> loadData(DTO dto, PaginationTemplate pagination) {
					return loadMainResult(dto, pagination);
				};
	
				@Override
				public DTO rowSelect(Long id) {			
					return actionEdit(id);
				};	
			};
		
	}	
	
	public void checkPermision(){
		habilitaCrear		();
		habilitaEditar		();
		habilitaGuardar		();
		habilitaEliminar	();
		habilitaReportar	();
		deshabilitaSolicitar();
		deshabilitaAprobar	();
		deshabilitaRechazar	();
		deshabilitaRecuperar();
		deshabilitaReversar	();	
		deshabilitaGenerar	();
		
		enableControls();
	}
	
	
	
	protected void habilitaCrear(){
		habilitaCrear=true;
	}	
	protected void deshabilitaCrear(){
		habilitaCrear=false;
	}
	protected void habilitaEditar(){
		habilitaEditar=true;
	}	
	protected void deshabilitaEditar(){
		habilitaEditar=false;
	}
	protected void habilitaGuardar(){
		habilitaGuardar=true;
	}	
	protected void deshabilitaGuardar(){
		habilitaGuardar=false;
	}
	protected void habilitaEliminar(){
		habilitaEliminar=true;
	}	
	protected void deshabilitaEliminar(){
		habilitaEliminar=false;
	}
	protected void habilitaReportar(){
		habilitaReportar=true;
	}	
	protected void deshabilitaReportar(){
		habilitaReportar=false;
	}
	protected void habilitaSolicitar(){
		habilitaSolicitar=true;
	}	
	protected void deshabilitaSolicitar(){
		habilitaSolicitar=false;
	}
	protected void habilitaAprobar(){
		habilitaAprobar=true;
	}	
	protected void deshabilitaAprobar(){
		habilitaAprobar=false;
	}
	protected void habilitaRechazar(){
		habilitaRechazar=true;
	}	
	protected void deshabilitaRechazar(){
		habilitaRechazar=false;
	}
	protected void habilitaRecuperar(){
		habilitaRecuperar=true;
	}	
	protected void deshabilitaRecuperar(){
		habilitaRecuperar=false;
	}
	protected void habilitaReversar(){
		habilitaReversar=true;
	}	
	protected void deshabilitaReversar(){
		habilitaReversar=false;
	}
	protected void habilitaGenerar(){
		habilitaGenerar=true;
	}	
	protected void deshabilitaGenerar(){
		habilitaGenerar=false;
	}
	
	public void actionCreateRedirect(){
		setCurrentDTO(actionNew());	
		setSearchId(null);
		checkPermision();
		onRedirect();
	}
	
	public void actionCreate(){
		setCurrentDTO(actionNew());	
		setSearchId(null);
		checkPermision();
		onRedirect();
	}
	

	public void actionDelete(){
		actionDelete(currentDTO);
		checkPermision();
	}
	
	public void actionEditRedirect(){
		setSearchId(getCurrentDTO().getId());
		checkPermision();
		onRedirect();
	}

	public void actionEdit(){
		setSearchId(getCurrentDTO().getId());
		checkPermision();
		onRedirect();
	}
	public void actionGoBack(){
	}
	
	public void actionSave(ActionEvent evt){
		currentDTO=actionSave(currentDTO);
		setSearchId(getCurrentDTO().getId());
		checkPermision();
	}
	
	public void actionAprobar(ActionEvent evt){
		actionAprobar(currentDTO);
		checkPermision();
	}
	
	public void actionRechazar(ActionEvent evt){
		actionRechazar(currentDTO);
		checkPermision();
	}
	
	public void actionSolicitarAprobacion(ActionEvent evt){
		actionSolicitarAprobacion(currentDTO);
		checkPermision();
	}
	
	public void actionReversar(ActionEvent evt){
		actionReversar(currentDTO);
		checkPermision();
	}
	public void actionRecuperar(ActionEvent evt){
		actionRecuperar(currentDTO);
		checkPermision();
	}
	public void actionSearchId(){
		currentDTO=actionEdit(getSearchId());
	}
	
	
	
	public DTO getSearchDTO() {
		searchDTO=searchDTO!=null?searchDTO:instanceSearchDTO();
		return searchDTO;
	}
	
	public void setSearchDTO(DTO searchDTO) {
		this.searchDTO = searchDTO;
	}
	
	
	public List<DTO> getSearchResult() {
		searchResult=searchResult!=null?searchResult:loadMainResult(getSearchDTO(),getPagination());
		return searchResult;
	}
		
	
	public PaginationTemplate getPagination() {
		return pagination;
	}
	
	public void setPagination(PaginationTemplate pagination) {
		this.pagination = pagination;
	}

	public LazyDataModel<DTO> getDataModel() {
		return dataModel;
	}

	public void setDataModel(LazyDataModel<DTO> model) {
		this.dataModel = model;
	}
	


}
