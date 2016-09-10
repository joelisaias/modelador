package ec.edu.ug.mb;

import java.math.BigDecimal;

import org.primefaces.context.RequestContext;

import ec.edu.ug.util.GenericManagedBean;

public class TemplateMB extends GenericManagedBean {

	private static final long serialVersionUID = 9127180840504035585L;
	
	
	
	

	public RequestContext getPrimeRequestContext(){
		return RequestContext.getCurrentInstance();
	}
	
	public void updatePrimeComponent(String clientId){
		getPrimeRequestContext().update(String.format("#{p:component('%s')}", clientId));
	}
	
	public void excecutePrime(String script){
		getPrimeRequestContext().execute(script);
	}
	
	
	protected static BigDecimal ZERO		= new BigDecimal(0);
	protected static BigDecimal ONE			= new BigDecimal(1);
	protected static BigDecimal TEN			= new BigDecimal(10);
	protected static BigDecimal TWELVE		= new BigDecimal(12);
	protected static BigDecimal ONE_HUNDRED	= new BigDecimal(100);
		
}