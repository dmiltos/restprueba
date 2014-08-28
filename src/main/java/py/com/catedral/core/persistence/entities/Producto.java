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
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Esta clase representa la entidad Producto del dominio
 * @author Pablo
 *
 */

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "Producto.callInventarioStoreProcedure",
//	query = "begin GET_SINGLE_PRODUCT_INFO(?, ?, ?, ?, ?); end;")
	query = "{? = call GET_PRODUCT_INFO(:P_CODIGO) }", resultClass = Producto.class, hints = {
		@javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true") })
})
@Entity
public class Producto implements Serializable{

	private static final long serialVersionUID = 2977497127815369634L;

	@Id
	@NotNull(message="{producto.codigo_producto_not_null}")
	@Size(max=10,message="{producto.codigo_producto_size}")
	private String codigo;

	@NotNull(message="{producto.descripcion_producto_not_null}")
	@Size(max=100,message="{producto.descripcion_producto_size}")
	private String descripcion;

	@NotNull(message="{producto.vencimiento_producto_not_null}")
	@Size(max=10,message="{producto.vencimiento_producto_size}")
	private String vencimiento;

	@NotNull(message="{producto.lote_producto_not_null}")
	@Size(max=50,message="{producto.lote_producto_size}")
	private String lote;

	@NotNull(message="{producto.cantidad_producto_not_null}")
	@Size(max=10,message="{producto.cantidad_producto_size}")
	private String cantidad;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo == null ? null : codigo.trim();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion == null ? null : descripcion.trim();
	}

	public String getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote == null ? null : lote.trim();
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad == null ? "0" : cantidad;
	}
	
	
}
