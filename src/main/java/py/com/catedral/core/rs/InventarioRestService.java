package py.com.catedral.core.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;

import py.com.catedral.core.exceptions.AppException;
import py.com.catedral.core.exceptions.BusinessException;
import py.com.catedral.core.persistence.entities.Producto;
import py.com.catedral.core.services.commons.InventarioService;

@Path("/inventario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class InventarioRestService {
	
	@Inject
	private InventarioService inventarioService;

	@GET
	@Path("/{parametro}")
	public Response devolverMensaje(@PathParam("parametro") String msg) {

		String salida = "Ejemplo restful con Jersey : " + msg;

		return Response.status(200).entity(salida).build();

	}

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Alumno getAlumno() {
		Alumno alu = new Alumno();
		alu.setId(17);
		alu.setCedula("1234567");
		alu.setNombre("Matteo");
		alu.setApellido("Rodr�guez");
		return alu;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public DetalleParametroDTO getDetalleParametro(){
		DetalleParametroDTO dp = new DetalleParametroDTO();
		dp.setAbreviatura("ASU");
		dp.setDescripcion("ASUNCION");
		dp.setId(15L);
		return dp;
	}
	
	@GET
	@Path("/pelicula")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie getMovie() {
		Movie mov = new Movie();
		mov.setTitle("StarWars");
		mov.setDirector("Yo");
		mov.setReleaseYear("1990");
		mov.setGenre("Science Fiction");
		return mov;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/inventariar")
	public Response inventariar(ParametrosInventario params, @Context HttpServletRequest request){
		//Tomamos las credenciales en base64
		Producto x = null;
		try {
			x = inventarioService.inventariar(params.getCodigoBarras(), 
					params.getCodigoInventario(), 0, "catedral", "catedral");
			
		} catch (AppException | BusinessException e) {
			
			e.printStackTrace();
		}
		
		return Response.ok(x).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/auth")
	public Response login(CredencialesCliente params, @Context HttpServletRequest request){
		//Tomamos las credenciales en base64
//		System.out.println(request.getHeader("Authorization"));
		
//		System.out.println(new String(Base64.decodeBase64(request.getHeader("Authorization").getBytes())));
		
		inventarioService.login(params.getUsuario(), params.getClave());
		
		return Response.ok().build();
	}
}