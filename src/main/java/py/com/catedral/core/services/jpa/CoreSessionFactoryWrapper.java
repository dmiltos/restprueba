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
 * Clase wrraper para conexiones via JPA
 *
 */
@Singleton
public class CoreSessionFactoryWrapper {//extends SessionFactoryWrapper {

	protected final Configuration config = ConfigurationFactory.getConfig();

	private EntityManagerFactory factory;

	public CoreSessionFactoryWrapper() throws IOException {
	}

	private EntityManagerFactory getEntityManagerFactory(String usuario, String clave){
		if (factory == null){
			Map<String, Object> properties = new HashMap<>();
			properties.put("javax.persistence.jdbc.url", config.getJdbcURL());
			properties.put("javax.persistence.jdbc.driver", config.getJdbcDriver());
			properties.put("javax.persistence.jdbc.dialect", config.getJdbcDialect());
			properties.put("javax.persistence.jdbc.Schema", config.getJdbcSchema());
			properties.put("javax.persistence.jdbc.user", usuario);
			properties.put("javax.persistence.jdbc.password", clave);

			properties.put("connection.pool_size", config.getConnectionPoolSize());
			factory =
					Persistence.createEntityManagerFactory("Core-JpaPersistenceUnit", properties);
		}
		return factory;
	}

	public EntityManager getEntityManager(String usuario, String clave){
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.user", usuario);
		properties.put("javax.persistence.jdbc.password", clave);

		return this.getEntityManagerFactory(usuario, clave).createEntityManager(properties);
	}

}
