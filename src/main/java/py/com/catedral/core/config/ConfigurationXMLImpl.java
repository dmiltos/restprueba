package py.com.catedral.core.config;


public class ConfigurationXMLImpl implements Configuration
{
	
	private String jdbcURL;
	private String jdbcDriver;
	private String jdbcDialect;
	private String jdbcSchema;
	private String connectionPoolSize;
	
	@Override
	public String getJdbcURL() {
		// TODO Auto-generated method stub
		return jdbcURL;
	}
	public void setJdbcURL(String jdbcURL) {
		this.jdbcURL = jdbcURL;
	}
	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}
	public void setJdbcDialect(String jdbcDialect) {
		this.jdbcDialect = jdbcDialect;
	}
	public void setJdbcSchema(String jdbcSchema) {
		this.jdbcSchema = jdbcSchema;
	}
	public void setConnectionPoolSize(String connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
	}
	@Override
	public String getJdbcDriver() {
		// TODO Auto-generated method stub
		return jdbcDriver;
	}
	@Override
	public String getJdbcDialect() {
		// TODO Auto-generated method stub
		return jdbcDialect;
	}
	@Override
	public String getJdbcSchema() {
		// TODO Auto-generated method stub
		return jdbcSchema;
	}
	@Override
	public String getConnectionPoolSize() {
		// TODO Auto-generated method stub
		return connectionPoolSize;
	}

}