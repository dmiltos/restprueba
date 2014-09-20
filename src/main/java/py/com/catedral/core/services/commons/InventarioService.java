package py.com.catedral.core.services.commons;

import static py.com.catedral.core.services.utils.ValidationUtil.validarArgumento;

import java.sql.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

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
			if (x != null){
				logger.debug("SE AUTENTICO EL USUARIO " + usuario + " a las " + x.toString());
				
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Procedimiento para inventariar productos
	 * 
	 * @param codigoDeBarras
	 * @param codigoDeInventario
	 * @param indicadorManual
	 * @param estado
	 * @param cantidad
	 * @param lote
	 * @param vencimiento
	 * @param indicadorTipoEvento
	 * @param usuario
	 * @param clave
	 * @return
	 * @throws AppException
	 * @throws BusinessException
	 */
	public Producto inventariar(String codigoDeBarras, Long codigoDeInventario, String indicadorManual, String estado, 
			Float cantidad, String lote, java.util.Date vencimiento, String indicadorTipoEvento,
			String usuario, String clave) throws AppException, BusinessException {

		validarArgumento(codigoDeBarras, "El objeto codigo de barras no puede ser nulo");

		try {

			Query q = factory.getEntityManager(usuario, clave).createNamedQuery("Producto.callInventarioStoreProcedure");
			
			q.setParameter("p_cod_barra", codigoDeBarras); // IN parameter
			q.setParameter("p_cod_inventario", codigoDeInventario); // IN parameter
			Date fechaProceso = new Date(System.currentTimeMillis());
			q.setParameter("p_fec_proceso", fechaProceso); // IN parameter
			q.setParameter("p_ind_manual", indicadorManual); // IN parameter
			q.setParameter("p_estado", estado); // IN parameter
			q.setParameter("p_cantidad", cantidad); // IN parameter
			q.setParameter("p_lote", lote); // IN parameter
			q.setParameter("p_ind_tipo_evento", indicadorTipoEvento != null && !"".equals(indicadorTipoEvento.trim()) ? indicadorTipoEvento : "WS"); // IN parameter, envia WS por defecto
			Date fechaVencimiento = null;
			if (vencimiento != null){
				fechaVencimiento = new Date(vencimiento.getTime());
			}
			q.setParameter("p_vencimiento", fechaVencimiento); // IN parameter
			
			Producto prod = null;
			logger.debug("CODIGO DE BARRAS:" + codigoDeBarras);
			logger.debug("CODIGO DE INVENTARIO:" + codigoDeInventario);
			logger.debug("FECHA DE PROCESO:" + fechaProceso);
			logger.debug("CARGA MANUAL?:" + indicadorManual);
			logger.debug("ESTADO:" + estado);
			logger.debug("CANTIDAD:" + cantidad);
			logger.debug("LOTE:" + lote);
			logger.debug("VENCIMIENTO:" + vencimiento);

			try {
			  prod = (Producto)q.getSingleResult();
			  logger.debug("SE INVENTARIO UN PRODUCTO CON LOS SIGUIENTES DATOS:" + prod);
				
			} catch (Exception ae) {
				logger.error("NO SE PUDIERON LEER DATOS DEL PROCEDIMIENTO");
			}

			return prod;
		
		} catch (Exception ae) {
			logger.error("OCURRIO UN ERROR AL INVENTARIAR:" + ae.getMessage());
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

}
