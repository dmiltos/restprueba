package py.com.catedral.core.services.commons;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import py.com.catedral.core.exceptions.AppException;
import py.com.catedral.core.exceptions.BusinessException;
import py.com.catedral.core.persistence.entities.Empleado;
import py.com.catedral.core.persistence.entities.Proveedor;
import py.com.catedral.core.services.jpa.CoreSessionFactoryWrapper;

@Stateless(name = "PickingService", description = "Servicios para la elaboración de picking")
public class PickingService {
	


	@EJB
	private CoreSessionFactoryWrapper factory;

	public List<Empleado> getEmpleadosDeposito(String user, String pass) throws AppException, BusinessException {
		List<Empleado> empleados = new ArrayList<Empleado>();
		Empleado e = new Empleado();
		e.setCodigo(123L);
		e.setNombre("DEEEERRRLISSS");
		empleados.add(e);
		e = new Empleado();
		e.setCodigo(456L);
		e.setNombre("AAANIIIBAALLLL");
		empleados.add(e);
		e = new Empleado();
		e.setCodigo(789L);
		e.setNombre("PAAAABLOOOOOOO");
		empleados.add(e);
		return empleados;
	}

	public List<Proveedor> getProveedoresByIdOC(String user, String pass, Long id) throws AppException, BusinessException {
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		Proveedor e = new Proveedor();
		e.setCodigo(111L);
		e.setNombre("ETICOS");
		proveedores.add(e);
		e = new Proveedor();
		e.setCodigo(222L);
		e.setNombre("SCAVONE HNOS.");
		proveedores.add(e);
		e = new Proveedor();
		e.setCodigo(333L);
		e.setNombre("LASCA");
		proveedores.add(e);
		return proveedores;
	}
	
	public List<Proveedor> getProveedoresByIdRepo(String user, String pass, Long id) throws AppException, BusinessException {
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		Proveedor e = new Proveedor();
		e.setCodigo(333L);
		e.setNombre("LASCA");
		proveedores.add(e);
		e = new Proveedor();
		e.setCodigo(222L);
		e.setNombre("SCAVONE HNOS.");
		proveedores.add(e);
		
		return proveedores;
	}

}
