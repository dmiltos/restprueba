package py.com.xsys.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import py.com.xsys.model.Producto;
import py.com.xsys.model.Usuario;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AndroidRestService {
	
	@POST
	@Path("login")
	public Response autenticarUsuario(Usuario user){
		
		if (user.getUsuario().equals("system"))
			if (user.getPassword().equals("manager"))
				return Response.ok("Ingreso Exitoso").build();
			else
				return Response.status(Status.FORBIDDEN).entity("Password Invalido").build();
		else 
			return Response.status(Status.FORBIDDEN).entity("Usuario Invalido").build();
	}

	@GET
	@Path("productos")
	public Response getDatos() {
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Producto producto = new Producto();
		producto.setId(100);
		producto.setFabricante("google");
		producto.setModelo("nexus4");
		productos.add(producto);
		producto = new Producto();
		producto.setId(200);
		producto.setFabricante("samsung");
		producto.setModelo("galaxy S5");
		productos.add(producto);
		producto = new Producto();
		producto.setId(300);
		producto.setFabricante("sony");
		producto.setModelo("xperia");
		productos.add(producto);
		producto = new Producto();
		producto.setId(400);
		producto.setFabricante("amazon");
		producto.setModelo("firephone");
		productos.add(producto);
		
		return Response.ok().entity(productos).build();
	}
}
