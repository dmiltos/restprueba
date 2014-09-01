package py.com.catedral.core.rs;

import java.util.HashMap;
import java.util.Map;

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
import javax.ws.rs.core.Response.Status;

import org.apache.commons.codec.binary.Base64;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;

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
		
//		if (inventarioService.login(params.getUsuario(), params.getClave())){
		
			// Create JWS payload
			Payload payload = new Payload(params.getUsuario());
	
			// Create JWS header with HS256 algorithm
			JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
	
			// Create JWS object
			JWSObject jwsObject = new JWSObject(header, payload);
	
			// Create HMAC signer
			String sharedKey = "a0a2abd8616241c383d61cf559b46afc";
	
			JWSSigner signer = new MACSigner(sharedKey.getBytes());
			try {
				jwsObject.sign(signer);
			} catch (JOSEException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			// Serialise JWS object to compact format
			String token = jwsObject.serialize();
			System.out.println("Serialised JWS object: " + token);
			Map<String,String> res = new HashMap<String, String>();
			res.put("token", token);
			
			return Response
					.ok()
					.entity(res)
					.build();
//		}
//		else{
//			return Response.status(Status.FORBIDDEN).build();
//		}
	}
}