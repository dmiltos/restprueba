package py.com.catedral.core.services.jpa;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * 
 * Clase wrraper para conexiones via MyBatis
 *
 */
@Singleton
public class CoreSessionFactoryWrapper {//extends SessionFactoryWrapper {
	
	private EntityManager em;

	public CoreSessionFactoryWrapper() throws IOException {
	}

	public EntityManager getEntityManager(String usuario, String clave){
//		if (em == null){
			Map<String, Object> properties = new HashMap<>();
//			properties.put("javax.persistence.jdbc.url", "jdbc:oracle:thin:@127.0.0.1:1521:XE");
//			properties.put("javax.persistence.jdbc.driver", "oracle.jdbc.driver.OracleDriver");
//			properties.put("javax.persistence.jdbc.dialect", "org.hibernate.dialect.Oracle10gDialect");
//			properties.put("javax.persistence.jdbc.Schema", "CATEDRAL");
//			properties.put("javax.persistence.jdbc.user", usuario);
//			properties.put("javax.persistence.jdbc.password", clave);
			
			properties.put("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver");
			properties.put("hibernate.connection.url", "jdbc:oracle:thin:@127.0.0.1:1521:XE");
			properties.put("hibernate.connection.username", usuario);
			properties.put("hibernate.connection.password", clave);
			properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
			properties.put("hibernate.default_schema", "CATEDRAL");
			properties.put("show_sql", "true");
			properties.put("connection.pool_size", "1");
			EntityManagerFactory emf =
					Persistence.createEntityManagerFactory("Core-JpaPersistenceUnit", properties);
			em = emf.createEntityManager();
//		}
		return em;
	}

}
