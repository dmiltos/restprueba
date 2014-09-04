package py.com.catedral.core.services.commons;

import static py.com.catedral.core.services.utils.ValidationUtil.validarArgumento;

import java.sql.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import py.com.catedral.core.exceptions.AppException;
import py.com.catedral.core.exceptions.BusinessException;
import py.com.catedral.core.persistence.entities.Producto;
import py.com.catedral.core.services.jpa.CoreSessionFactoryWrapper;

/**
 * Esta clase provee m&eacute;todos para operaciones de toma de inventario de productos
 * 
 * @author Pablo
 * 
 */

@Stateless(name = "InventarioService", description = "Servicios para la toma de inventarios")
public class InventarioService {

	@EJB
	private CoreSessionFactoryWrapper factory;

	/**
	 * Este m&eacute;todo env&iacute;a el coacute;digo del producto leiacute;do al procedimiento almacenado
	 * 
	 * @param producto 
	 * 			Coacute;digo del producto leiacute;d
	 *            
	 * @return
	 * @throws AppException
	 *             Cuando el argumento {@code codigoDeBarras} es {@code null} se propaga
	 *             una instancia de @{code AppException.IllegalArgument}, Cuando ocurra un error
	 *             inesperado durante la ejecuci&oacute;n se propaga una instancia de
	 * @throws BusinessException
	 *             cuando el argumento {@code codigoDeBarras} viole alguna restricci&oacute;n
	 *             del dominio.
	 */
	
	public boolean login(String usuario, String clave){
		Object sysdate = null;
		try{
		Query q = factory.getEntityManager(usuario, clave).createNativeQuery("SELECT SYSDATE FROM DUAL");
		sysdate = q.getSingleResult();
		}catch(PersistenceException e){
			return false;
		}
		if (sysdate != null){
			java.sql.Timestamp x = (java.sql.Timestamp)sysdate;
			System.out.println(x);
			return true;
		}
		return false;
	}
	
	/**
	 * Procedimiento funcionando
	 * 
	 * @param codigoDeBarras
	 * @param codigoDeInventario
	 * @param cantidad
	 * @param usuario
	 * @param clave
	 * @return
	 * @throws AppException
	 * @throws BusinessException
	 */
	public Producto inventariar(String codigoDeBarras, Long codigoDeInventario, Integer cantidad,
			String usuario, String clave) throws AppException, BusinessException {

		validarArgumento(codigoDeBarras, "El objeto codigo de barras no puede ser nulo");

		try {

			Query q = factory.getEntityManager(usuario, clave).createNamedQuery("Producto.callInventarioStoreProcedure");
			
			q.setParameter("P_CODIGO_BARRAS", codigoDeBarras); // IN parameter
			q.setParameter("P_CODIGO_INVENTARIO", codigoDeInventario); // IN parameter
			q.setParameter("P_FECHA_PROCESO", new Date(System.currentTimeMillis())); // IN parameter
			
			Producto prod = (Producto) q.getSingleResult();
			
			return prod;
		
		} catch (Exception ae) {
			throw new AppException.InternalError(ae.getMessage());
		} finally {
			
		}
	}

	/*public Producto inventariar(String codigoDeBarras, String codigoDeInventario, Integer cantidad,
			String usuario, String clave) throws AppException, BusinessException {

		validarArgumento(codigoDeBarras, "El objeto codigo de barras no puede ser nulo");

		try {
			// Nos aseguramos de que el producto corresponda al codigo de inventario
			//LLAMAMOS AL PROCEDIMIENTO QUE VERIFICA QUE EL PRODUCTO ESTE EN EL CODIGO DE INVENTARIO
			
			
			Connection c = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", usuario, clave);
//			CallableStatement cs = c.prepareCall("CALL GET_SINGLE_PRODUCT_INFO(:P_CODIGO, :P_DESCRIPCION, :P_VENCIMIENTO, :P_LOTE, :P_CANTIDAD)");
			CallableStatement cs = c.prepareCall("begin GET_SINGLE_PRODUCT_INFO(?, ?, ?, ?, ?); end;");
			cs.setString(1, codigoDeBarras);
			cs.registerOutParameter(2, Types.VARCHAR);
//
			cs.registerOutParameter(3, Types.VARCHAR);
//
			cs.registerOutParameter(4, Types.VARCHAR);
//
			cs.registerOutParameter(5, Types.VARCHAR);
			
			cs.execute();

			System.out.println(cs.getString(2));
			

			return new Producto();

		} catch (Exception ae) {
			throw new AppException.InternalError(ae.getMessage());
		} finally {
			
		}
	}*/




	/**
	 * Este m&eacute;todo obtiene todos los registros de la tabla 
	 * @return				 lista de ciudades encontradas
	 */
	//	public List<Ciudades> listar() {
	//		SqlSession session = null;
	//		try {
	//			session = factory.getSqlSession();
	//
	//			InventarioMapper mapper = session.getMapper(InventarioMapper.class);
	//			List<Ciudades> list = mapper.selectByExample(null);
	//			return list;
	//		} finally {
	//			session.close();
	//		}
	//	}
	//
	//	
	//	public Integer total() {
	//		SqlSession session = null;
	//		try {
	//
	//			session = factory.getSqlSession();
	//			InventarioMapper mapper = session.getMapper(InventarioMapper.class);
	//			int total = mapper.countByExample(null);
	//			return total;
	//
	//		} finally {
	//			session.close();
	//		}
	//	}

}
