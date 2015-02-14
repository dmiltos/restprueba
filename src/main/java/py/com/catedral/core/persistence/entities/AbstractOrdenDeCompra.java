package py.com.catedral.core.persistence.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * Clase común entre las entidades de tipo Orden de Compra
 * pero no es mapeada como una entidad
 * @author pablo
 *
 */
@MappedSuperclass
public abstract class AbstractOrdenDeCompra implements Serializable{
	
	private static final long serialVersionUID = 5360093288731749102L;

	@Id
	@NotNull(message="{orden_de_compra.numero_orden_not_null}")
	@Column(name="p_numero_orden")
	protected Long numeroOrden;
	
	@NotNull(message="{orden_de_compra.fecha_not_null}")
	@Column(name="p_fecha_orden")
	protected Date fechaOrden;
	
	@NotNull(message="{orden_de_compra.sucursal_not_null}")
	@Column(name="p_sucursal")
	protected String sucursal;

	public Long getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(Long numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public Date getFechaOrden() {
		return fechaOrden;
	}

	public void setFechaOrden(Date fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((numeroOrden == null) ? 0 : numeroOrden.hashCode());
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
		AbstractOrdenDeCompra other = (AbstractOrdenDeCompra) obj;
		if (numeroOrden == null) {
			if (other.numeroOrden != null)
				return false;
		} else if (!numeroOrden.equals(other.numeroOrden))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrdenDeCompra [numeroOrden=" + numeroOrden + ", fechaOrden="
				+ fechaOrden + ", sucursal=" + sucursal + "]";
	}
}
