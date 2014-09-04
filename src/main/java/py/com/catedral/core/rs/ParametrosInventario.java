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

}
