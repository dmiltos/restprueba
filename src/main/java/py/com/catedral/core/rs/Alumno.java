/*
 * $Id$
 * ===========================================================================
 * Copyright 2014 BCP
 * Todos los derechos reservados.
 * ===========================================================================
 */
 
/*
 * Historial de cambio:
 *
 * Fecha             	Origen        	Descripcion
 * ----             	------        	--------------------------------------------------
 * 04/07/2014  			Pablo			Primera Version
 */
 
package py.com.catedral.core.rs;

import javax.xml.bind.annotation.XmlRootElement;
 
/**
 * Esta clase representa ...
 * @author Pablo
 *
 */
@XmlRootElement 
public class Alumno {
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String cedula;
	private String nombre;
	private String apellido;
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
