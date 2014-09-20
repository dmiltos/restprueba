package py.com.catedral.core.services.jpa;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import py.com.catedral.core.config.Configuration;
import py.com.catedral.core.config.ConfigurationFactory;
/**
 * 
 * Clase wrraper para conexiones via MyBatis
 *
 */
@Singleton
public class CoreSessionFactoryWrapper {//extends SessionFactoryWrapper {
	
	protected final Configuration config = ConfigurationFactory.getConfig();
	
	private EntityManager em;

	public CoreSessionFactoryWrapper() throws IOException {
	}

	public EntityManager getEntityManager(String usuario, String clave){
			Map<String, Object> properties = new HashMap<>();
			properties.put("javax.persistence.jdbc.url", config.getJdbcURL());
			properties.put("javax.persistence.jdbc.driver", config.getJdbcDriver());
			properties.put("javax.persistence.jdbc.dialect", config.getJdbcDialect());
			properties.put("javax.persistence.jdbc.Schema", config.getJdbcSchema());
			properties.put("javax.persistence.jdbc.user", usuario);
			properties.put("javax.persistence.jdbc.password", clave);

			properties.put("connection.pool_size", config.getConnectionPoolSize());
			EntityManagerFactory emf =
					Persistence.createEntityManagerFactory("Core-JpaPersistenceUnit", properties);
			em = emf.createEntityManager();
		return em;
	}

}
