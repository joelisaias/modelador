package ec.edu.ug.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.jdal.dao.jpa.JpaUtils;

import ec.edu.ug.model.AbstractEntity;
import ec.edu.ug.util.PaginationTemplate;

public abstract class AbstractDAO <T extends AbstractEntity, PK extends Number> {

private Class<T> entityClass;

	public AbstractDAO() {
	}
	public AbstractDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T save(T e) {
		if (e.getId() != null)
			return getEntityManager().merge(e);
		else {
			getEntityManager().persist(e);
			return e;
		}
	}

	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	
	public T find(PK id) {
		return getEntityManager().find(entityClass, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findRange(int[] range) {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int count() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		Query q = getEntityManager().createQuery(cq);		
		return ((Long) q.getSingleResult()).intValue();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> findByCriteriaQuery(CriteriaQuery<T> cq){
		//cq.from(entityClass);
		Query q = getEntityManager().createQuery(cq);
		return q.getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<T> findByCriteriaQuery(CriteriaQuery<T> cq,PaginationTemplate pagination){
		if(pagination==null){
			return findByCriteriaQuery(cq);
		}		
		List<T> results=new ArrayList<T>();
		buildPaginationCriteria(cq, pagination);
		//cq.select(cq.from(entityClass));
		Query q = getEntityManager().createQuery(cq);
		q.setFirstResult(pagination.getFirstRow());
		q.setMaxResults(pagination.getPageSize());		
		results=q.getResultList();		
		
		return results;

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void buildPaginationCriteria(CriteriaQuery criteria,PaginationTemplate pagination){
		CriteriaQuery<Long> criteriaRows = JpaUtils.countCriteria(getEntityManager(), criteria);
		Number totalRows=0;
		if(criteriaRows!=null){
			Query q = getEntityManager().createQuery(criteriaRows);		

			totalRows=(Number)q.getSingleResult();
		}
		pagination.setRowCount(totalRows==null?0:totalRows.intValue());		
	}
	

	protected CriteriaQuery<T> createCriteriaQuery(){
		return getEntityManager().getCriteriaBuilder().createQuery(entityClass);
	}
	
	protected CriteriaBuilder getCriteriaBuilder(){
		return getEntityManager().getCriteriaBuilder();
	}
	

	public Metamodel getMetaModel(){
		return getEntityManager().getMetamodel();	
	}
	
	public EntityType<T> getEntityType(){
		return getMetaModel().entity(entityClass);
	}
	
	
	/**
	 * Exige a definiçion de <code>EntityManager</code> responsable de las operaçiones de persistencia.
	 */
	protected abstract EntityManager getEntityManager();

}
