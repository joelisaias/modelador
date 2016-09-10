package ec.edu.ug.util;

import java.security.Principal;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class JSFUtils implements IConstantes {
	
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}   

	public static HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) getFacesContext().getExternalContext()
				.getRequest();
	}
	
	public static HttpServletResponse getHttpServletResponse() {
		return (HttpServletResponse) getFacesContext().getExternalContext()
				.getResponse();
	}
	
	public static HttpSession getHttpSession() {
		return getHttpServletRequest().getSession();
	}
	
	public static String getRealPath(String pRutaRelativa){
		return getHttpServletRequest().getSession().getServletContext().getRealPath(pRutaRelativa);
	}
	
	public static ServletContext getServletContext(){
		return getHttpServletRequest().getServletContext();
	}
	
	public static ExternalContext getExternalContext(){
		return getFacesContext().getExternalContext();
	}

	public static String getContextPath() {
		return getHttpServletRequest().getContextPath();
	}

	public static Application getApplication() {
		return getFacesContext().getApplication();
	}
	
	public static <T> T findManagedBean(String managedBeanName, Class<T> beanClass){
		return beanClass.cast(getApplication().evaluateExpressionGet(getFacesContext(), "#{".concat(managedBeanName).concat("}"), beanClass));
	}

	public static FacesMessage buildMessage(String _sumary, String _detail,
			FacesMessage.Severity _severity) {
		FacesMessage l_fm = new FacesMessage();
		l_fm.setSummary(_sumary);
		l_fm.setDetail(_detail);
		l_fm.setSeverity(_severity);
		return l_fm;
	}

	public static FacesMessage buildMessageError(String _message, String _detail) {
		return buildMessage(_message, _detail,
				FacesMessage.SEVERITY_ERROR);
	}

	public static FacesMessage buildMessageError(String _message) {
		return buildMessageError(_message, "");
	}

	public static FacesMessage buildMessageInfo(String _message,
			String _detail) {
		return buildMessage(_message, _detail,
				FacesMessage.SEVERITY_INFO);
	}

	public static FacesMessage buildMessageInfo(String _messaje) {
		return buildMessageInfo(_messaje, "");
	}
	
	public static FacesMessage buildMessageWarn(String _message,
			String _detail) {
		return buildMessage(_message, _detail,
				FacesMessage.SEVERITY_WARN);
	}

	public static FacesMessage buildMessageWarn(String _messaje) {
		return buildMessageWarn(_messaje, "");
	}

	public static FacesMessage buildMessageFatal(String _message,
			String _detail) {
		return buildMessage(_message, _detail,
				FacesMessage.SEVERITY_FATAL);
	}

	public static FacesMessage buildMessageFatal(String _messaje) {
		return buildMessageFatal(_messaje, "");
	}
	
	public static void addMessage(FacesMessage _message, String _clienteId) {
		getFacesContext().addMessage(_clienteId, _message);
	}

	public static void addMessage(FacesMessage _menssage) {
		addMessage(_menssage, null);
	}

	/*************************************************
	 * 	SEVERITY INFO
	 *************************************************/
	public static void addMessageInfo(String _message,
			String _detail) {
		addMessage(buildMessageInfo(_message,_detail));
	}

	public static void addMessageInfo(String pMensajeInformacion) {
		addMessageInfo(pMensajeInformacion, "");
	}

	public static void addMessageInfoComponent(String _clientId,
			String _message, String _detail) {
		addMessage(buildMessageInfo(_message, _detail),_clientId);
	}

	public static void addMessageInfoComponent(String _clientId,
			String _message) {
		addMessageInfoComponent(_clientId, _message,"");
	}
		
	/*************************************************
	 * 	SEVERITY WARN
	 *************************************************/
	public static void addMessageWarn(String _message,String _detail) {
		addMessage(buildMessageWarn(_message,_detail));
	}

	public static void addMessageWarn(String _message) {
		addMessageWarn(_message, "");
	}

	public static void addMessageWarnComponent(String _clientId,
			String _message, String _detail) {
		addMessage(buildMessageWarn(_message, _detail),_clientId);
	}

	public static void addMessageWarnComponent(String _clientId,
			String _message) {
		addMessageWarnComponent(_clientId, _message,"");
	}

	/*************************************************
	 * 	SEVERITY ERROR
	 *************************************************/
	public static void addMessageError(String _sumary, String _detail) {
		addMessage(buildMessageError(_sumary, _detail));
	}

	public static void addMessageError(String _sumary) {
		addMessageError(_sumary, "");
	}

	public static void addMessageErrorComponent(String _clientId, String _sumary, String _detail) {
		addMessage(buildMessageError(_sumary, _detail), _clientId);
	}

	public static void addMessageErrorComponent(String _clientId, String _sumary) {
		addMessage(buildMessageError(_sumary), _clientId);
	}

	public static void addMessageError(Throwable _exception) {
		_exception.printStackTrace();
		addMessageError(_exception.getMessage());
		
	}

	/*************************************************
	 * 	SEVERITY FATAL
	 *************************************************/
	public static void addMessageFatal(String _message,String _detail) {
		addMessage(buildMessageFatal(_message,_detail));
	}

	public static void addMessageFatal(String _message) {
		addMessageFatal(_message, "");
	}

	public static void addMessageFatalComponent(String _clientId,
			String _message, String _detail) {
		addMessage(buildMessageFatal(_message, _detail),_clientId);
	}

	public static void addMessageFatalComponent(String _clientId,
			String _message) {
		addMessageFatalComponent(_clientId, _message,"");
	}


	public static void setRequestAttribute(String pAttribute, Object pValor) {
		getFacesContext().getExternalContext().getRequestMap()
				.put(pAttribute, pValor);
	}

	public static void clearRequestAttribute(String pAttribute) {
		getFacesContext().getExternalContext().getRequestMap()
				.remove(pAttribute);
	}
	
	public static void removeRequestValue(Object pValue) {
		removeValueFromMap(pValue,getFacesContext().getExternalContext().getRequestMap());
	}

	private static void removeValueFromMap(Object pValue,Map<?,?> pMap){
		Object lClaveEncontrada=null;
		if(pMap.containsValue(pValue)){
			for(Object lClave:pMap.keySet()){
				if(pMap.get(lClave).equals(pValue)){
					lClaveEncontrada=lClave;
					break;
				}
			}
			pMap.remove(lClaveEncontrada);
		}
	}
	
	public static void setSessionAttribute(String pAttribute, Object pValor) {
		getFacesContext().getExternalContext().getSessionMap()
				.put(pAttribute, pValor);
	}

	public static void clearSessionAttribute(String pAttribute) {
		getFacesContext().getExternalContext().getSessionMap()
				.remove(pAttribute);
	}
	
	public static void removeSessionValue(Object pValue) {
		removeValueFromMap(pValue,getFacesContext().getExternalContext().getSessionMap());
	}

	public static Object getRequestAttribute(String pAttribute) {
		return getFacesContext().getExternalContext().getRequestMap()
				.get(pAttribute);
	}

	public static Object getSessionAttribute(String pAttribute) {
		return getFacesContext().getExternalContext().getSessionMap()
				.get(pAttribute);
	}

	public static String getRequestParameter(String pParameter) {
		return getFacesContext().getExternalContext().getRequestParameterMap()
				.get(pParameter);
	}

	public static String[] getRequestParameters(String pParameter) {
		return getFacesContext().getExternalContext()
				.getRequestParameterValuesMap().get(pParameter);
	}

	public static boolean isAutenticado() {
		return getHttpServletRequest()!=null && getHttpServletRequest().getUserPrincipal() != null;
	}
	
	public static boolean isUserInRole(String pRol) {
		return ((HttpServletRequest) getFacesContext().getExternalContext()
				.getRequest()).isUserInRole(pRol);
	}

	public static Principal getUsuarioAutenticado() {
		return ((HttpServletRequest) getFacesContext().getExternalContext()
				.getRequest()).getUserPrincipal();
	}

	public static String getNombreUsuarioAutenticado() {
		Principal lPrincipal = getUsuarioAutenticado();
		String lNombreUsuario = null;
		if (lPrincipal != null)
			lNombreUsuario = lPrincipal.getName();
		return lNombreUsuario;
	}

	public static Locale getDefaultLocale() {
		return Locale.getDefault();
	}

	public static Locale getClientLocale() {
		return getFacesContext().getViewRoot().getLocale();
	}

	public static boolean isFacesContexConError() {
		Severity lSeverity = getFacesContext().getMaximumSeverity();
		return FacesMessage.SEVERITY_ERROR.equals(lSeverity)
				|| FacesMessage.SEVERITY_FATAL.equals(lSeverity);
	}
	
	public static void removerFacesMessage(FacesMessage.Severity pSeverity){
		for(Iterator<FacesMessage>i=getFacesContext().getMessages();i.hasNext();){
			FacesMessage lFacesMessage=i.next();
			if(lFacesMessage.getSeverity().equals(pSeverity))
				i.remove();
		}
	}
	
	public static void removerFacesMessageInformacion(){
		removerFacesMessage(FacesMessage.SEVERITY_INFO);
	}
	public static void setNombreArchivoDescargar(String pNombreArchivo){
		getHttpServletResponse().setHeader("Content-Disposition", "inline; filename=\""
				+ pNombreArchivo + "\"");
	}
	public static void setContentType(String pTipoContenido){
		getHttpServletResponse().setContentType(pTipoContenido);
	}
	public static void setContentTypeArchivoTexto(){
		getHttpServletResponse().setContentType("application/txt");
	}
	
	public void removeSessionBean(final String beanName) {
		getFacesContext().getExternalContext().getSessionMap().remove(beanName);
	}
	
	public void removeManagedBean(final String beanName) {
	    FacesContext fc = FacesContext.getCurrentInstance();
	    fc.getELContext().getELResolver().setValue(fc.getELContext(), null, beanName, null);
	}
	
	public Map<String, String> getParametersMap(){
		return getFacesContext().getExternalContext().getRequestParameterMap();
	}
	
	public String getParameterValue(String parameterName){
		return getParametersMap().get(parameterName);	
	}
	
	public Long getParameterLongValue(String parameterName){
		String valorString = getParameterValue(parameterName);
		if(valorString == null) return null;
		return new Long(valorString);	
	}
	
	public Map<String, Object> getSessionMap(){
		return getFacesContext().getExternalContext().getSessionMap();
	}
	

	public String getQueryString(){		
		return getHttpServletRequest().getQueryString();
	}
	
	@SuppressWarnings("unchecked")
	protected <K> K getValueSession(String property, Class<K> Clazz){
		return (K) getSessionMap().get(property);
	}
	
	protected <K> void putValueSession(String property, K value){
		getSessionMap().put(property, value);
	}

	public void redirect(String outcome) {
		try {
			if(getFacesContext()!=null)
				getFacesContext().getExternalContext().redirect(getContextPath()+outcome);
		} catch (Exception e) {
			addMessageError(e.getMessage());
		}
    }
}
