package py.com.catedral.core.config;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationFactory
{
    private static Logger log = LoggerFactory.getLogger(ConfigurationFactory.class);
    private static Configuration config = null;

    private static String CONFIGFILE = null;
    

    public static Configuration getConfig()
    {
        if (config == null)
        {
            config = load();
        }
        return config;
    }

    public static void reload()
    {
        synchronized (config)
        {
            config = null;
        }
    }

    private static Configuration load()
    {
        try
        {
            Digester digester = new Digester();

            ConfigurationXMLImpl conf = new ConfigurationXMLImpl();
            digester.push(conf);

            digester.addBeanPropertySetter("corews/persistence/jdbc-url","jdbcURL");
			digester.addBeanPropertySetter("corews/persistence/jdbc-driver","jdbcDriver");
			digester.addBeanPropertySetter("corews/persistence/jdbc-dialect","jdbcDialect");
            digester.addBeanPropertySetter("corews/persistence/jdbc-schema","jdbcSchema");
            digester.addBeanPropertySetter("corews/persistence/connection-pool-size", "connectionPoolSize");

            String filePath = getConfigfile();
            File file = new File(filePath);
            
            if(file.exists()) 
            {
                digester.parse(new FileInputStream(filePath));
            }
            else
            {
                log.error("No se cuentra el archivo de configuracion en:");
                log.error(filePath);
            }
            
            return conf;
        }
        catch (Exception e)
        {
            log.error("error reading configuration", e);
            throw new RuntimeException(e); // should not continue
        }
    }
    
    public static String getConfigfile()
    {
        if(CONFIGFILE == null)
        {
            String serverConfigFolder = null;
            
            serverConfigFolder = "JBOSS_SERVER_CONFIG_URL";
            
            String folderurl = fixUrlName(System.getenv(serverConfigFolder));
            
            CONFIGFILE = folderurl + "/apps/catedral/corews-config.xml";
            //CONFIGFILE = "/opt/jboss-eap-6.1/domain/configuration/apps/djbws/djb-config.xml";
        }

        return CONFIGFILE;
    }

    
    public static String fixUrlName(String folderurl)
    {
        if(folderurl.contains("file:")) {
            String[] split = folderurl.split("file:");
            folderurl = split[1];
        }
        
        return folderurl;
    }

}
