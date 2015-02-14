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
 * Esta clase representa la entidad Orden de Compra Asignada a un Empleado.
 * Es utilizada para mostrar los datos de una orden de compra asignada
 * a un empleado, asi como el pasillo, en el proceso de picking
 * 
 * @author Pablo
 *
 */

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "OrdenDeCompraAsignada.callObtenerOrdenesCompraAsignadasSP",
	query = "{call PA_OBTENER_ORDENES_COMPRA_ASIGNADAS(?, ?)}", 
		resultClass = OrdenDeCompraAsignada.class, hints = {
			@javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true") 
		})
})
@Entity
public class OrdenDeCompraAsignada extends AbstractOrdenDeCompra implements Serializable{

	private static final long serialVersionUID = -6944162655899182170L;
	
	@NotNull(message="{orden_de_compra_asignada.pasillo_not_null}")
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
				+ ((this.getNumeroOrden() == null) ? 0 : this.getNumeroOrden().hashCode());
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
		OrdenDeCompraAsignada other = (OrdenDeCompraAsignada) obj;
		if (numeroOrden == null) {
			if (other.numeroOrden != null)
				return false;
		} else if (!numeroOrden.equals(other.numeroOrden))
			return false;
		return true;
	}
}
