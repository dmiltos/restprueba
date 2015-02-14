/*
 * $Id$
 * ===========================================================================
 * Copyright 2014 Eidos S.A.
 * Todos los derechos reservados.
 * ===========================================================================
 */

/*
 * Historial de cambio:
 *
 * Fecha             	Origen        	Descripcion
 * ----             	------        	--------------------------------------------------
 * 29/07/2014  			Pablo			Primera Version
 */

package py.com.catedral.core.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

/**
 * Esta clase representa la entidad Pedido de Reposición Pendiente.
 * Es utilizada para mostrar los datos de un pedido de reposición pendiente
 * en el proceso de picking
 * @author Pablo
 *
 */

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "PedidoDeReposicionPendiente.callObtenerPedidosReposicionPendientesSP",
	query = "{call PA_OBTENER_PEDIDOS_REPOSICION_PENDIENTES(?)}", 
		resultClass = PedidoDeReposicionPendiente.class, hints = {
			@javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true") 
		})
})
@Entity
public class PedidoDeReposicionPendiente extends AbstractPedidoDeReposicion implements Serializable{

	private static final long serialVersionUID = -4033082717209289334L;

}
