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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.validation.constraints.NotNull;

/**
 * Esta clase representa la entidad Pedido de Reposicion Asignado.
 * Es utilizada para mostrar los datos de un pedido de reposicion asignado
 * a un empleado, asi como el pasillo, en el proceso de picking
 * 
 * @author Pablo
 *
 */

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "PedidoDeReposicionAsignado.callObtenerPedidosReposicionAsignadosSP",
	query = "{call PA_OBTENER_PEDIDOS_REPOSICION_ASIGNADOS(?, ?)}", 
		resultClass = PedidoDeReposicionAsignado.class, hints = {
			@javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true") 
		})
})
@Entity
public class PedidoDeReposicionAsignado extends AbstractPedidoDeReposicion implements Serializable{

	private static final long serialVersionUID = -8503725607605002643L;

	@NotNull(message="{pedido_de_reposicion_asignado.pasillo_not_null}")
	@Column(name="p_pasillo")
	private String pasillo;

	public String getPasillo() {
		return pasillo;
	}
	
	public void setPasillo(String pasillo) {
		this.pasillo = pasillo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.getNumeroPedido() == null) ? 0 : this.getNumeroPedido().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoDeReposicionAsignado other = (PedidoDeReposicionAsignado) obj;
		if (numeroPedido == null) {
			if (other.numeroPedido != null)
				return false;
		} else if (!numeroPedido.equals(other.numeroPedido))
			return false;
		return true;
	}
}
