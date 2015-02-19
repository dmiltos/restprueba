package py.com.catedral.core.config;

public interface Configuration {

	/**
	 * Devuelve la cadena de conexion a la BD
	 * @return
	 */
    public String getJdbcURL();
	
    /**
     * Devuelve el nombre de la clase Driver
     * @return
     */
	public String getJdbcDriver();
	
	/**
	 * Devuelve el dialecto del gestor de BD
	 * @return
	 */
	public String getJdbcDialect();
	
	/**
	 * Devuelve el esquema al cual se tendra que conectar
	 * @return
	 */
	public String getJdbcSchema();
	
	/**
	 * Devuelve el tamaño del pool de conexiones
	 * @return
	 */
	public String getConnectionPoolSize();
    
}
