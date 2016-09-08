package ec.edu.ug.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ug.model.Producto;

@Stateless
@LocalBean
public class ProductoDAO extends AbstractDAO<Producto,Long> {

	@PersistenceContext
	private EntityManager em;
	
	public ProductoDAO(Class<Producto> entityClass) {
		super(entityClass);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}

