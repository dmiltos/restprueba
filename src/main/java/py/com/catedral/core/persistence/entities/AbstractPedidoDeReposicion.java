package py.com.catedral.core.persistence.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * Clase comon entre las entidades de tipo Pedido de Reposicion
 * pero no es mapeada como una entidad
 * @author pablo
 *
 */
@MappedSuperclass
public abstract class AbstractPedidoDeReposicion implements Serializable{

	private static final long serialVersionUID = 5072269996590628999L;

	@Id
	@NotNull(message="{pedido_de_reposicion.numero_pedido_not_null}")
	@Column(name="p_numero_pedido")
	protected Long numeroPedido;
	
	@NotNull(message="{pedido_de_reposicion.fecha_not_null}")
	@Column(name="p_fecha_pedido")
	protected Date fechaPedido;
	
	@NotNull(message="{pedido_de_reposicion.sucursal_not_null}")
	@Column(name="p_sucursal")
	protected String sucursal;

	public Long getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
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
				+ ((numeroPedido == null) ? 0 : numeroPedido.hashCode());
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
		AbstractPedidoDeReposicion other = (AbstractPedidoDeReposicion) obj;
		if (numeroPedido == null) {
			if (other.numeroPedido != null)
				return false;
		} else if (!numeroPedido.equals(other.numeroPedido))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PedidoDeReposicion [numeroPedido=" + numeroPedido
				+ ", fechaPedido=" + fechaPedido + ", sucursal=" + sucursal
				+ "]";
	}
}
