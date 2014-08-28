package py.com.catedral.core.persistence.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import py.com.catedral.core.persistence.entities.Producto;

/**
 * 
 * @author pablo
 *
 */
public interface InventarioDAO {
    
//    public List<Proveedor> getProveedores(String filtroNombre) throws DataAccessException;
    public Producto chequearProductoValidoYTomarInventario
    	(String codigoDeBarras, String codigoInventario) 
    			throws DataAccessException;
    
    public List<Producto> getProductos() throws DataAccessException;
}