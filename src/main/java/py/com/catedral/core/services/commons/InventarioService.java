package py.com.catedral.core.services.commons;

import static py.com.catedral.core.services.utils.ValidationUtil.validarArgumento;

import java.sql.Connection;
import java.sql.DriverManager;

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

	public Producto inventariar(String codigoDeBarras, String codigoDeInventario, Integer cantidad,
			String usuario, String clave) throws AppException, BusinessException {

		validarArgumento(codigoDeBarras, "El objeto codigo de barras no puede ser nulo");

		try {
			// Nos aseguramos de que el producto corresponda al codigo de inventario
			//LLAMAMOS AL PROCEDIMIENTO QUE VERIFICA QUE EL PRODUCTO ESTE EN EL CODIGO DE INVENTARIO
			
			
//			Connection c = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", usuario, clave);
//			CallableStatement cs = c.prepareCall("CALL GET_SINGLE_PRODUCT_INFO(:P_CODIGO, :P_DESCRIPCION, :P_VENCIMIENTO, :P_LOTE, :P_CANTIDAD)");
//			CallableStatement cs = c.prepareCall("begin GET_SINGLE_PRODUCT_INFO(?, ?, ?, ?, ?); end;");
			Query q = factory.getEntityManager(usuario, clave).createNamedQuery("Producto.callInventarioStoreProcedure");
//			cs.setString(1, codigoDeBarras);
//			cs.registerOutParameter(2, Types.VARCHAR);
//
//			cs.registerOutParameter(3, Types.VARCHAR);
//
//			cs.registerOutParameter(4, Types.VARCHAR);
//
//			cs.registerOutParameter(5, Types.VARCHAR);
			
//			q.setParameter(1, result);
			q.setParameter("P_CODIGO", codigoDeBarras); // IN parameter
//			q.setParameter("codigoInventario", codigoDeInventario); // IN parameter
//			q.setParameter(2, descripcion); // OUT parameter
//			q.setParameter(3, vencimiento); // OUT parameter
//			q.setParameter(4, lote); // OUT parameter
//			q.setParameter(5, cant); // OUT parameter
//			cs.execute();
			Producto prod = (Producto) q.getSingleResult();
//			System.out.println(cs.getString(2));
//			cs.set
			
//			Producto singleResult = (Producto) q.getSingleResult();
//			final List<Map<String, Object>> results = mapper.chequearProductoValidoParaInventario(params);
			return prod;
//			for(final Map<String, Object> producto : results)
//			{
//				p = new Producto();
//				System.out.println(producto.get("title"));
//			}

//			if (params.getTotalFilas() <= 0){
//				throw new AppException.ResourceNotFoundException("El producto que desea inventariar no corresponde al codigo de inventario.");
//			}else{
//				
//			}

		} catch (Exception ae) {
			throw new AppException.InternalError(ae.getMessage());
		} finally {
			
		}
		//		try {
		//
		//			session = factory.getSqlSession();
		//			CiudadesMapper mapper = session.getMapper(CiudadesMapper.class);
		//			mapper.insert(ciudad);
		//
		//		} catch (Exception e) {
		//			throw new AppException.InternalError(e.getMessage());
		//		} finally {
		//			if (session != null) {
		//				session.close();
		//			}
		//		}
		//		return ciudad;

	}




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
