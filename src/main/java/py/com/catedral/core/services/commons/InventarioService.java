package py.com.catedral.core.services.commons;

import static py.com.catedral.core.services.utils.ValidationUtil.validarArgumento;

import java.sql.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

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
		EntityManager em = factory.getEntityManager(usuario, clave);
		try{
			Query q = em.createNativeQuery("SELECT SYSDATE FROM DUAL");
			sysdate = q.getSingleResult();
			
		}catch(PersistenceException e){
			return false;
		}
		finally{
			this.cerrarSesion(em);
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
	
	private void cerrarSesion(EntityManager em) {
		if (em != null && em.isOpen()){
			em.close();
		}
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
			Float cantidad, String lote, String vencimiento, String indicadorTipoEvento, Long codigoDeBarrasManual,
			String usuario, String clave) throws AppException, BusinessException {
		
		if (Strings.isNullOrEmpty(codigoDeBarras) && codigoDeBarrasManual == null){
			validarArgumento(codigoDeBarras, "El objeto codigo de barras no puede ser nulo");
		}
		EntityManager em = factory.getEntityManager(usuario, clave);

		try {

			Query q = em.createNamedQuery("Producto.callInventarioStoreProcedure");
			
			q.setParameter("p_cod_barra", codigoDeBarras != null && !"".equals(codigoDeBarras) ? codigoDeBarras : codigoDeBarrasManual != null ? codigoDeBarrasManual.toString() : ""); // IN parameter
			q.setParameter("p_cod_inventario", codigoDeInventario); // IN parameter
			Date fechaProceso = new Date(System.currentTimeMillis());
			q.setParameter("p_fec_proceso", fechaProceso, TemporalType.TIMESTAMP); // IN parameter
			q.setParameter("p_ind_manual", indicadorManual); // IN parameter
			q.setParameter("p_estado", estado); // IN parameter
			q.setParameter("p_cantidad", cantidad); // IN parameter
			q.setParameter("p_lote", lote); // IN parameter
			q.setParameter("p_ind_tipo_evento", indicadorTipoEvento != null && !"".equals(indicadorTipoEvento.trim()) ? indicadorTipoEvento : "WS"); // IN parameter, envia WS por defecto
			q.setParameter("p_vencimiento", vencimiento == null ? "" : vencimiento); // IN parameter
//			q.setParameter("p_cod_barra_manual", codigoDeBarrasManual == null ? 0 : codigoDeBarrasManual);
			
//			Date fechaVencimiento = null;
//			if (vencimiento != null){
//				fechaVencimiento = new Date(vencimiento.getTime());
//				q.setParameter("p_vencimiento", fechaVencimiento, TemporalType.DATE); // IN parameter
//			}else{
//				q.setParameter("p_vencimiento", fechaProceso, TemporalType.TIMESTAMP); // IN parameter
//			}
			
			Producto prod = null;
			logger.info("CODIGO DE BARRAS: " + codigoDeBarras);
			logger.info("CODIGO DE INVENTARIO: " + codigoDeInventario);
			logger.info("FECHA DE PROCESO: " + fechaProceso);
			logger.info("CARGA MANUAL?: " + indicadorManual);
			logger.info("ESTADO: " + estado);
			logger.info("CANTIDAD: " + cantidad);
			logger.info("LOTE: " + lote);
			logger.info("VENCIMIENTO: " + vencimiento);
			logger.info("COD BARRAS MANUAL:" + codigoDeBarrasManual);

			try {
			  Object o = q.getSingleResult();
			  logger.info("OBJETO RESULTANTE: " + o);
			  prod = (Producto)o;
			  logger.info("SE INVENTARIO UN PRODUCTO CON LOS SIGUIENTES DATOS: " + prod);
				
			} catch (Exception ae) {
				logger.error("NO SE PUDIERON LEER DATOS DEL PROCEDIMIENTO");
			}

			return prod;
		
		} catch (Exception ae) {
			logger.error("OCURRIO UN ERROR AL INVENTARIAR:" + ae.getMessage());
			throw new AppException.InternalError(ae.getMessage());
		} finally {
			this.cerrarSesion(em);
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
