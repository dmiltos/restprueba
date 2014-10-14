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
 * 15/08/2014  			pablo			Primera Version
 */
 
package py.com.catedral.core.rs;

import javax.xml.bind.annotation.XmlRootElement;
 
/**
 * @author pablo
 *
 */
@XmlRootElement 
public class ParametrosInventario extends CredencialesCliente{
	
	private String codigoBarras;
	private Long codigoInventario;
	private String indicadorManual;
	private Float cantidad;
	private String lote;
	private String vencimiento;
	private Long codigoDeBarrasManual;
	
	/**
	 * @return the codigoBarras
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}
	/**
	 * @param codigoBarras the codigoBarras to set
	 */
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
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
	 * @return the indicadorManual
	 */
	public String getIndicadorManual() {
		return indicadorManual;
	}
	/**
	 * @param indicadorManual the indicadorManual to set
	 */
	public void setIndicadorManual(String indicadorManual) {
		this.indicadorManual = indicadorManual;
	}
	/**
	 * @return the cantidad
	 */
	public Float getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the lote
	 */
	public String getLote() {
		return lote;
	}
	/**
	 * @param lote the lote to set
	 */
	public void setLote(String lote) {
		this.lote = lote;
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
	public Long getCodigoDeBarrasManual() {
		// TODO Auto-generated method stub
		return this.codigoDeBarrasManual;
	}
	/**
	 * @param codigoDeBarrasManual the codigoDeBarrasManual to set
	 */
	public void setCodigoDeBarrasManual(Long codigoDeBarrasManual) {
		this.codigoDeBarrasManual = codigoDeBarrasManual;
	}
	

}

