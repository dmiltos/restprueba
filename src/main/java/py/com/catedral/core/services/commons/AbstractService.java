package py.com.catedral.core.services.commons;

import javax.ejb.EJB;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import py.com.catedral.core.services.jpa.CoreSessionFactoryWrapper;

public abstract class AbstractService {
		
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@EJB
	protected CoreSessionFactoryWrapper factory;

	protected void cerrarSesion(EntityManager em) {
		if (em != null && em.isOpen()){
			em.close();
		}
	}

}
