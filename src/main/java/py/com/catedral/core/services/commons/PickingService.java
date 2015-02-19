package py.com.catedral.core.services.commons;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;

import py.com.catedral.core.exceptions.AppException;
import py.com.catedral.core.exceptions.BusinessException;
import py.com.catedral.core.persistence.entities.Empleado;
import py.com.catedral.core.persistence.entities.OrdenDeCompraAsignada;
import py.com.catedral.core.persistence.entities.OrdenDeCompraPendiente;
import py.com.catedral.core.persistence.entities.PedidoDeReposicionAsignado;
import py.com.catedral.core.persistence.entities.PedidoDeReposicionPendiente;
import py.com.catedral.core.persistence.entities.Proveedor;
import py.com.catedral.core.services.jpa.CoreSessionFactoryWrapper;

@Stateless(name = "PickingService", description = "Servicios para la elaboracion de picking")
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

	public List<OrdenDeCompraPendiente> getOrdenesDeCompraPendientes(String user,
			String pass) throws AppException, BusinessException {
		List<OrdenDeCompraPendiente> ordenesDeCompra = new ArrayList<OrdenDeCompraPendiente>();
		OrdenDeCompraPendiente o = new OrdenDeCompraPendiente();
		o.setFechaOrden(new java.sql.Date(115,0,10));
		o.setNumeroOrden(3996671L);
		o.setSucursal("VILLA MORRA");
		ordenesDeCompra.add(o);
		o = new OrdenDeCompraPendiente();
		o.setFechaOrden(new java.sql.Date(115,0,19));
		o.setNumeroOrden(3996672L);
		o.setSucursal("CENTRO");
		ordenesDeCompra.add(o);
		o = new OrdenDeCompraPendiente();
		o.setFechaOrden(new java.sql.Date(115,0,19));
		o.setNumeroOrden(3996673L);
		o.setSucursal("CENTRO");
		ordenesDeCompra.add(o);
		o = new OrdenDeCompraPendiente();
		o.setFechaOrden(new java.sql.Date(117,1,19));
		o.setNumeroOrden(3996673L);
		o.setSucursal("HIPODROMO");
		ordenesDeCompra.add(o);
		return ordenesDeCompra;
	}

	public List<PedidoDeReposicionPendiente> getPedidosDeReposicionPendientes(String user,
			String pass) throws AppException, BusinessException {
		List<PedidoDeReposicionPendiente> pedidosDeRepo = new ArrayList<PedidoDeReposicionPendiente>();
		PedidoDeReposicionPendiente pr = new PedidoDeReposicionPendiente();
		pr.setFechaPedido(new java.sql.Date(115,0,10));
		pr.setNumeroPedido(4545654L);
		pr.setSucursal("VILLA MORRA");
		pedidosDeRepo.add(pr);
		pr = new PedidoDeReposicionPendiente();
		pr.setFechaPedido(new java.sql.Date(115,0,19));
		pr.setNumeroPedido(4545655L);
		pr.setSucursal("CENTRO");
		pedidosDeRepo.add(pr);
		pr = new PedidoDeReposicionPendiente();
		pr.setFechaPedido(new java.sql.Date(115,0,19));
		pr.setNumeroPedido(4545656L);
		pr.setSucursal("SAN LORENZO");
		pedidosDeRepo.add(pr);
		return pedidosDeRepo;
	}

	public List<OrdenDeCompraAsignada> getOrdenesDeCompraAsignadasPorEmpleado(
			String user, String pass) throws AppException, BusinessException {
		List<OrdenDeCompraAsignada> ordenesDeCompra = new ArrayList<OrdenDeCompraAsignada>();
		OrdenDeCompraAsignada o = new OrdenDeCompraAsignada();
		o.setFechaOrden(new java.sql.Date(115,0,10));
		o.setNumeroOrden(3996671L);
		o.setSucursal("VILLA MORRA");
		o.setPasillo("PASILLO 1");
		ordenesDeCompra.add(o);
		o = new OrdenDeCompraAsignada();
		o.setFechaOrden(new java.sql.Date(115,0,19));
		o.setNumeroOrden(3996672L);
		o.setSucursal("CENTRO");
		o.setPasillo("PASILLO 1");
		ordenesDeCompra.add(o);
		o = new OrdenDeCompraAsignada();
		o.setFechaOrden(new java.sql.Date(115,0,19));
		o.setNumeroOrden(3996673L);
		o.setSucursal("CENTRO");
		o.setPasillo("PASILLO 2");
		ordenesDeCompra.add(o);
		return ordenesDeCompra;
	}

	public List<PedidoDeReposicionAsignado> getPedidosDeReposicionAsignadosPorEmpleado(
			String user, String pass) throws AppException, BusinessException {
		List<PedidoDeReposicionAsignado> pedidosDeReposicion = new ArrayList<PedidoDeReposicionAsignado>();
		PedidoDeReposicionAsignado pr = new PedidoDeReposicionAsignado();
		pr.setFechaPedido(new java.sql.Date(2015,10,01));
		pr.setNumeroPedido(4545654L);
		pr.setSucursal("VILLA MORRA");
		pr.setPasillo("PASILLO 1");
		pedidosDeReposicion.add(pr);
		pr = new PedidoDeReposicionAsignado();
		pr.setFechaPedido(new java.sql.Date(2015,01,19));
		pr.setNumeroPedido(4545655L);
		pr.setSucursal("CENTRO");
		pr.setPasillo("PASILLO 2");
		pedidosDeReposicion.add(pr);
		pr = new PedidoDeReposicionAsignado();
		pr.setFechaPedido(new java.sql.Date(2015,01,19));
		pr.setNumeroPedido(4545656L);
		pr.setSucursal("SAN LORENZO");
		pr.setPasillo("PASILLO 3");
		pedidosDeReposicion.add(pr);
		return pedidosDeReposicion;
	}

	public List<String> getPasillosDeposito(String user, String pass) throws AppException, BusinessException {
		List<String> pasillos = new ArrayList<String>();
		pasillos.add("PASILLO 1");
		pasillos.add("PASILLO 2");
		pasillos.add("PASILLO 3");
		return pasillos;
	}
}
