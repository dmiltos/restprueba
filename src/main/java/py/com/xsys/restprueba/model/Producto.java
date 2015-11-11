package py.com.xsys.restprueba.model;

import java.util.ArrayList;
import java.util.List;

public class Producto {

	public enum Red{
		GSM,
		HSPA,
		LTE
	}
	
	private Integer id;
	private String fabricante;
	private String modelo;
	private Red red;
	private Usuario usuario;
	
	private List<String> funcionalidades = new ArrayList<String>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public List<String> getFuncionalidades() {
		return funcionalidades;
	}
	public void addFuncionalidad(String funcionalidades) {
		this.funcionalidades.add(funcionalidades);
	}
	public Red getRed() {
		return red;
	}
	public void setRed(Red red) {
		this.red = red;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
	


}
