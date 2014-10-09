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
import java.sql.Date;

import javax.persistence.Column;
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
	query = "{call PA_INVENTARIO(?, :p_cod_barra, :p_cod_inventario, :p_fec_proceso, :p_ind_manual, :p_estado, :p_cantidad, :p_lote, :p_vencimiento, :p_ind_tipo_evento)}", 
		resultClass = Producto.class, hints = {
			@javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true") 
		})
})
@Entity
public class Producto implements Serializable{

	private static final long serialVersionUID = 2977497127815369634L;

	@Id
	@NotNull(message="{producto.codigo_producto_not_null}")
	@Size(max=10,message="{producto.codigo_producto_size}")
	@Column(name="p_cod_producto")
	private String codigoProducto;
	
	@NotNull(message="{producto.codigo_barra_not_null}")
	@Size(max=10,message="{producto.codigo_barra_size}")
	@Column(name="p_cod_barra")
	private String codigoBarra;
	
	@NotNull(message="{producto.codigo_inventario_not_null}")
	@Size(max=10,message="{producto.codigo_inventario_size}")
	@Column(name="p_cod_inventario")
	private Long codigoInventario;

	@NotNull(message="{producto.fecha_proceso_not_null}")
	@Size(max=10,message="{producto.fecha_proceso_size}")
	@Column(name="p_fec_proceso")
	private Date fechaProceso;
	
	@NotNull(message="{producto.indicador_exito_not_null}")
	@Size(max=100,message="{producto.indicador_exito_size}")
	@Column(name="p_ind_exito")
	private String indicadorExito;
	
	@NotNull(message="{producto.indicador_producto_leido_not_null}")
	@Size(max=100,message="{producto.indicador_producto_leido_size}")
	@Column(name="p_ind_prod_leido")
	private String indicadorProductoLeido;
	
	@NotNull(message="{producto.indicador_lote_leido_not_null}")
	@Size(max=100,message="{producto.indicador_lote_leido_size}")
	@Column(name="p_lote_leido")
	private String loteLeido;
	
	@NotNull(message="{producto.vencimiento_producto_not_null}")
	@Size(max=10,message="{producto.vencimiento_producto_size}")
	@Column(name="p_fec_venc_prod")
	private String vencimiento;
	
	@NotNull(message="{producto.nombre_producto_not_null}")
	@Size(max=100,message="{producto.nombre_producto_size}")
	@Column(name="p_nombre_prod")
	private String nombreProducto;

	@NotNull(message="{producto.codigo_proveedor_not_null}")
	@Size(max=10,message="{producto.codigo_proveedor_size}")
	@Column(name="p_prov_codigo")
	private Long codigoProveedor;
	
	@NotNull(message="{producto.nombre_proveedor_not_null}")
	@Size(max=100,message="{producto.nombre_proveedor_size}")
	@Column(name="p_prov_nombre")
	private String nombreProveedor;

	@NotNull(message="{producto.cantidad_acum_producto_not_null}")
	@Size(max=20,message="{producto.cantidad_acum_producto_size}")
	@Column(name="p_cant_acum")
	private Integer cantidadAcumuladaProducto;
	
	@NotNull(message="{producto.cantidad_leida_not_null}")
	@Size(max=20,message="{producto.cantidad_leida_size}")
	@Column(name="p_cantidad")
	private Integer cantidadLeida;
	
	@NotNull(message="{producto.total_inventario_not_null}")
	@Size(max=20,message="{producto.total_inventario_size}")
	@Column(name="p_total_inventario")
	private Integer totalInventario;
	
	@NotNull(message="{producto.total_cargado_not_null}")
	@Size(max=20,message="{producto.total_cargado_size}")
	@Column(name="p_total_cargado")
	private Integer totalCargado;
	
	@NotNull(message="{producto.total_restante_not_null}")
	@Size(max=20,message="{producto.total_restante_size}")
	@Column(name="p_falta")
	private Integer totalRestante;
	
	@NotNull(message="{producto.existencia_not_null}")
	@Size(max=20,message="{producto.existencia_size}")
	@Column(name="p_existencia")
	private Integer existencia;
	
//	@NotNull(message="{producto.indicador_tipo_evento_not_null}")
//	@Size(max=100,message="{producto.indicador_tipo_evento_size}")
//	@Column(name="p_ind_tipo_evento")
//	private String indicadorTipoEvento;
//	
//	@NotNull(message="{producto.indicador_tipo_ingreso_not_null}")
//	@Size(max=100,message="{producto.indicador_tipo_ingreso_size}")
//	@Column(name="p_ind_tipo_ingreso")
//	private String indicadorTipoIngreso;
//
//	public String getIndicadorTipoEvento() {
//		return indicadorTipoEvento;
//	}
//	public void setIndicadorTipoEvento(String indicadorTipoEvento) {
//		this.indicadorTipoEvento = indicadorTipoEvento;
//	}
	
	/**
	 * @return the nombreProveedor
	 */
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	/**
	 * @param nombreProveedor the nombreProveedor to set
	 */
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}
	/**
	 * @return the codigoBarra
	 */
	public String getCodigoBarra() {
		return codigoBarra;
	}
	/**
	 * @param codigoBarra the codigoBarra to set
	 */
	public void setCodigoBarra(String codigoBarra) {
		//	codigoBarra == null ? null : codigoBarra.trim();
		this.codigoBarra = codigoBarra;
	}

	/**
	 * @return the codigoInventario
	 */
	public Long getCodigoInventario() {
		return codigoInventario;
	}

	/**
	 * @param codigoInventario the codigoInventario to set
	 */
	public void setCodigoInventario(Long codigoInventario) {
		this.codigoInventario = codigoInventario;
	}

	/**
	 * @return the fechaProceso
	 */
	public Date getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @param fechaProceso the fechaProceso to set
	 */
	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	/**
	 * @return the indicadorExito
	 */
	public String getIndicadorExito() {
		return indicadorExito;
	}

	/**
	 * @param indicadorExito the indicadorExito to set
	 */
	public void setIndicadorExito(String indicadorExito) {
		this.indicadorExito = indicadorExito;
	}

	/**
	 * @return the indicadorProductoLeido
	 */
	public String getIndicadorProductoLeido() {
		return indicadorProductoLeido;
	}

	/**
	 * @param indicadorProductoLeido the indicadorProductoLeido to set
	 */
	public void setIndicadorProductoLeido(String indicadorProductoLeido) {
		this.indicadorProductoLeido = indicadorProductoLeido;
	}

	/**
	 * @return the loteLeido
	 */
	public String getLoteLeido() {
		return loteLeido;
	}

	/**
	 * @param loteLeido the loteLeido to set
	 */
	public void setLoteLeido(String loteLeido) {
		this.loteLeido = loteLeido;
	}

	/**
	 * @return the vencimiento
	 */
	public String getVencimiento() {
		return vencimiento;
	}

	/**
	 * @param vencimiento the vencimiento to set
	 */
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	/**
	 * @return the nombreProducto
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}

	/**
	 * @param nombreProducto the nombreProducto to set
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	/**
	 * @return the codigoProveedor
	 */
	public Long getCodigoProveedor() {
		return codigoProveedor;
	}

	/**
	 * @param codigoProveedor the codigoProveedor to set
	 */
	public void setCodigoProveedor(Long codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}


	@Override
	public String toString() {
		return "Producto [codigoProducto=" + codigoProducto + ", codigoBarra="
				+ codigoBarra + ", codigoInventario=" + codigoInventario
				+ ", fechaProceso=" + fechaProceso + ", indicadorExito="
				+ indicadorExito + ", indicadorProductoLeido="
				+ indicadorProductoLeido + ", loteLeido=" + loteLeido
				+ ", vencimiento=" + vencimiento + ", nombreProducto="
				+ nombreProducto + ", codigoProveedor=" + codigoProveedor
				+ ", nombreProveedor=" + nombreProveedor + "]";
	}
	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	
	/**
	 * @return the cantidadLeida
	 */
	public Integer getCantidadLeida() {
		return cantidadLeida;
	}
	/**
	 * @param cantidadLeida the cantidadLeida to set
	 */
	public void setCantidadLeida(Integer cantidadLeida) {
		this.cantidadLeida = cantidadLeida;
	}
	/**
	 * @return the cantidadAcumuladaProducto
	 */
	public Integer getCantidadAcumuladaProducto() {
		return cantidadAcumuladaProducto;
	}
	/**
	 * @param cantidadAcumuladaProducto the cantidadAcumuladaProducto to set
	 */
	public void setCantidadAcumuladaProducto(Integer cantidadAcumuladaProducto) {
		this.cantidadAcumuladaProducto = cantidadAcumuladaProducto;
	}
	/**
	 * @return the totalInventario
	 */
	public Integer getTotalInventario() {
		return totalInventario;
	}
	/**
	 * @param totalInventario the totalInventario to set
	 */
	public void setTotalInventario(Integer totalInventario) {
		this.totalInventario = totalInventario;
	}
	/**
	 * @return the totalCargado
	 */
	public Integer getTotalCargado() {
		return totalCargado;
	}
	/**
	 * @param totalCargado the totalCargado to set
	 */
	public void setTotalCargado(Integer totalCargado) {
		this.totalCargado = totalCargado;
	}
	/**
	 * @return the totalRestante
	 */
	public Integer getTotalRestante() {
		return totalRestante;
	}
	/**
	 * @param totalRestante the totalRestante to set
	 */
	public void setTotalRestante(Integer totalRestante) {
		this.totalRestante = totalRestante;
	}
	
	/**
	 * @return the existencia
	 */
	public Integer getExistencia() {
		return existencia;
	}
	/**
	 * @param existencia the existencia to set
	 */
	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}
}
