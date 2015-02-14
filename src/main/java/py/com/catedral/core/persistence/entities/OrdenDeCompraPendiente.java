/*
 * $Id$
 * ===========================================================================
 * Copyright 2015 XSys S.A.
 * Todos los derechos reservados.
 * ===========================================================================
 */

/*
 * Historial de cambio:
 *
 * Fecha             	Origen        	Descripcion
 * ----             	------        	--------------------------------------------------
 * 01/02/2015  			Pablo			Primera Version
 */

package py.com.catedral.core.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

/**
 * Esta clase representa la entidad Orden de Compra Pendiente.
 * Es utilizada para mostrar los datos de una orden de compra pendiente
 * en el proceso de picking
 * 
 * @author Pablo
 *
 */

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "OrdenDeCompraPendiente.callObtenerOrdenesCompraPendientesSP",
	query = "{call PA_OBTENER_ORDENES_COMPRA_PENDIENTES(?)}", 
		resultClass = OrdenDeCompraPendiente.class, hints = {
			@javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true") 
		})
})
@Entity
public class OrdenDeCompraPendiente extends AbstractOrdenDeCompra implements Serializable{

	private static final long serialVersionUID = -6944162655899182170L;

}
