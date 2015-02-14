package py.com.catedral.core.rs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.minidev.json.JSONObject;
import py.com.catedral.core.exceptions.AppException;
import py.com.catedral.core.exceptions.BusinessException;
import py.com.catedral.core.persistence.entities.Empleado;
import py.com.catedral.core.persistence.entities.OrdenDeCompraAsignada;
import py.com.catedral.core.persistence.entities.OrdenDeCompraPendiente;
import py.com.catedral.core.persistence.entities.PedidoDeReposicionAsignado;
import py.com.catedral.core.persistence.entities.PedidoDeReposicionPendiente;
import py.com.catedral.core.persistence.entities.Proveedor;
import py.com.catedral.core.services.commons.PickingService;

@Path("picking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PickingRestService extends AbstractRestService{

	@Inject
	private PickingService pickingService;
	
	@GET
	@Path("ordenes-de-compra/pendientes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrdenesDeCompraPendientes(@Context HttpServletRequest request){
		Map<String,String> res = new HashMap<String, String>();
		
		Response resp = validarAuthHeader(request, res);
		if (resp != null) return resp;
		
		JSONObject jsonPayLoad = decodePayload(request);
		String user = (String) jsonPayLoad.get("sub");
		String pass = (String) jsonPayLoad.get("pss");
		
		List<OrdenDeCompraPendiente> ordenesDeCompra = null;
		try {
			ordenesDeCompra = pickingService.getOrdenesDeCompraPendientes(user, pass);
			
		} catch (AppException | BusinessException e) {			
			e.printStackTrace();
		}
		
		if (ordenesDeCompra != null){
			return Response.ok(ordenesDeCompra).build();
		}
		else{	
			res.put("message", "Datos no encontrados");
			return Response.status(Status.NOT_FOUND).entity(res).build();
		}
	}
	
	@GET
	@Path("ordenes-de-compra/asignadas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrdenesDeCompraAsignadasPorEmpleado(
			@Context HttpServletRequest request){
		
		Map<String,String> res = new HashMap<String, String>();
		
		Response resp = validarAuthHeader(request, res);
		if (resp != null) return resp;
		
		JSONObject jsonPayLoad = decodePayload(request);
		String user = (String) jsonPayLoad.get("sub");
		String pass = (String) jsonPayLoad.get("pss");
		
		List<OrdenDeCompraAsignada> ordenesDeCompra = null;
		try {
			ordenesDeCompra = pickingService.getOrdenesDeCompraAsignadasPorEmpleado(user, pass);
			
		} catch (AppException | BusinessException e) {			
			e.printStackTrace();
		}
		
		if (ordenesDeCompra != null){
			return Response.ok(ordenesDeCompra).build();
		}
		else{	
			res.put("message", "Datos no encontrados");
			return Response.status(Status.NOT_FOUND).entity(res).build();
		}
	}
	
	@GET
	@Path("pedidos-de-reposicion/pendientes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPedidosDeReposicionPendientes(@Context HttpServletRequest request){
		Map<String,String> res = new HashMap<String, String>();
		
		Response resp = validarAuthHeader(request, res);
		if (resp != null) return resp;
		
		JSONObject jsonPayLoad = decodePayload(request);
		String user = (String) jsonPayLoad.get("sub");
		String pass = (String) jsonPayLoad.get("pss");
		
		List<PedidoDeReposicionPendiente> pedidosDeRepo = null;
		try {
			pedidosDeRepo = pickingService.getPedidosDeReposicionPendientes(user, pass);
			
		} catch (AppException | BusinessException e) {			
			e.printStackTrace();
		}
		
		if (pedidosDeRepo != null){
			return Response.ok(pedidosDeRepo).build();
		}
		else{	
			res.put("message", "Datos no encontrados");
			return Response.status(Status.NOT_FOUND).entity(res).build();
		}
	}
	
	@GET
	@Path("pedidos-de-reposicion/asignados")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPedidosDeReposicionAsignados(@Context HttpServletRequest request){
		Map<String,String> res = new HashMap<String, String>();
		
		Response resp = validarAuthHeader(request, res);
		if (resp != null) return resp;
		
		JSONObject jsonPayLoad = decodePayload(request);
		String user = (String) jsonPayLoad.get("sub");
		String pass = (String) jsonPayLoad.get("pss");
		
		List<PedidoDeReposicionAsignado> pedidosDeReposicion = null;
		try {
			pedidosDeReposicion = pickingService.getPedidosDeReposicionAsignadosPorEmpleado(user, pass);
			
		} catch (AppException | BusinessException e) {			
			e.printStackTrace();
		}
		
		if (pedidosDeReposicion != null){
			return Response.ok(pedidosDeReposicion).build();
		}
		else{	
			res.put("message", "Datos no encontrados");
			return Response.status(Status.NOT_FOUND).entity(res).build();
		}
	}
	
	@GET
	@Path("empleados/deposito")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmpleadosDeposito(@Context HttpServletRequest request){
		Map<String,String> res = new HashMap<String, String>();
		
		Response resp = validarAuthHeader(request, res);
		if (resp != null) return resp;
		
		JSONObject jsonPayLoad = decodePayload(request);
		String user = (String) jsonPayLoad.get("sub");
		String pass = (String) jsonPayLoad.get("pss");
		
		List<Empleado> empleados = null;
		try {
			empleados = pickingService.getEmpleadosDeposito(user, pass);
			
		} catch (AppException | BusinessException e) {			
			e.printStackTrace();
		}
		
		if (empleados != null){
			return Response.ok(empleados).build();
		}
		else{	
			res.put("message", "Datos no encontrados");
			return Response.status(Status.NOT_FOUND).entity(res).build();
		}
	}
	
	@GET
	@Path("proveedores/orden-de-compra/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProvPorOC(@PathParam("id") Long id, @Context HttpServletRequest request){
		Map<String,String> res = new HashMap<String, String>();
		
		Response resp = validarAuthHeader(request, res);
		if (resp != null) return resp;
		
		JSONObject jsonPayLoad = decodePayload(request);
		String user = (String) jsonPayLoad.get("sub");
		String pass = (String) jsonPayLoad.get("pss");
		
		List<Proveedor> proveedores = null;
		try {
			proveedores = pickingService.getProveedoresByIdOC(user, pass, id);
			
		} catch (AppException | BusinessException e) {			
			e.printStackTrace();
		}
		
		if (proveedores != null){
			return Response.ok(proveedores).build();
		}
		else{	
			res.put("message", "Datos no encontrados");
			return Response.status(Status.NOT_FOUND).entity(res).build();
		}
	}
	
	@GET
	@Path("proveedores/pedido-de-reposicion/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProvPorRepo(@PathParam("id") Long id, @Context HttpServletRequest request){
		Map<String,String> res = new HashMap<String, String>();
		
		Response resp = validarAuthHeader(request, res);
		if (resp != null) return resp;
		
		JSONObject jsonPayLoad = decodePayload(request);
		String user = (String) jsonPayLoad.get("sub");
		String pass = (String) jsonPayLoad.get("pss");
		
		List<Proveedor> proveedores = null;
		try {
			proveedores = pickingService.getProveedoresByIdRepo(user, pass, id);
			
		} catch (AppException | BusinessException e) {			
			e.printStackTrace();
		}
		
		if (proveedores != null){
			return Response.ok(proveedores).build();
		}
		else{	
			res.put("message", "Datos no encontrados");
			return Response.status(Status.NOT_FOUND).entity(res).build();
		}
	}
	
	@GET
	@Path("pasillos/deposito")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPasillosDeposito(@Context HttpServletRequest request){
		Map<String,String> res = new HashMap<String, String>();
		
		Response resp = validarAuthHeader(request, res);
		if (resp != null) return resp;
		
		JSONObject jsonPayLoad = decodePayload(request);
		String user = (String) jsonPayLoad.get("sub");
		String pass = (String) jsonPayLoad.get("pss");
		
		List<String> pasillos = null;
		try {
			pasillos = pickingService.getPasillosDeposito(user, pass);
			
		} catch (AppException | BusinessException e) {			
			e.printStackTrace();
		}
		
		if (pasillos != null){
			return Response.ok(pasillos).build();
		}
		else{	
			res.put("message", "Datos no encontrados");
			return Response.status(Status.NOT_FOUND).entity(res).build();
		}
	}
}
