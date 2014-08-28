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

import org.apache.commons.codec.binary.Base64;
 
/**
 * @author pablo
 *
 */
public class CredencialesCliente {
	
	private String usuario;
	private String clave;
	
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
//		String decoded = Base64.decodeBase64(this.clave.getBytes()).toString();
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

}
