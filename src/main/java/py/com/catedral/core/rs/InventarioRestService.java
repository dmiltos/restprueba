package py.com.catedral.core.rs;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.minidev.json.JSONObject;
//import org.codehaus.jettison.json.JSONObject;
import py.com.catedral.core.exceptions.AppException;
import py.com.catedral.core.exceptions.BusinessException;
import py.com.catedral.core.persistence.entities.Producto;
import py.com.catedral.core.services.commons.InventarioService;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

@Path("/inventario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class InventarioRestService {
	
	@Inject
	private InventarioService inventarioService;	
	private String sharedKey = "a0a2abd8616241c383d61cf559b46afc";

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
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/inventariar")
	public Response inventariar(ParametrosInventario params, @Context HttpServletRequest request){
		//Tomamos las credenciales en base64
		Producto x = null;
		try {
			x = inventarioService.inventariar(params.getCodigoBarras(), 
					params.getCodigoInventario(), 0, "system", "manager");
			
		} catch (AppException | BusinessException e) {
			
			e.printStackTrace();
		}
		
		return Response.ok(x).build();
	}
	
	@GET
	@Path("/verifyAuth")
	public Response ensureAuthenticated(CredencialesCliente params, @Context HttpServletRequest request){
		
		Map<String,String> res = new HashMap<String, String>();
		
		// Parse back and check signature
		JWSObject jwsObject = null;
		try {
			jwsObject = JWSObject.parse(request.getHeader("authorization"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JWSVerifier verifier = new MACVerifier(sharedKey.getBytes());

		boolean verifiedSignature = false;
		try {
			verifiedSignature = jwsObject.verify(verifier);
		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (verifiedSignature)
			System.out.println("Verified JWS signature!");
		else
			System.out.println("Bad JWS signature!");
		System.out.println("Recovered payload message: " + jwsObject.getPayload());
		
		JSONObject jsonPayLoad = jwsObject.getPayload().toJSONObject();
		Long exp = (Long) jsonPayLoad.get("exp");
		
		if (exp <= System.currentTimeMillis()){
			res.put("message", "session expired");
			return Response
					.status(Status.UNAUTHORIZED)
					.entity(res)
					.build();
		}
		else{
			return Response
					.ok()
					.entity(res)
					.build();
		}

	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/auth")
	public Response login(CredencialesCliente params, @Context HttpServletRequest request){
		//Tomamos las credenciales en base64
//		System.out.println(request.getHeader("Authorization"));
		
//		System.out.println(new String(Base64.decodeBase64(request.getHeader("Authorization").getBytes())));
		Map<String,String> res = new HashMap<String, String>();
		
		if (inventarioService.login(params.getUsuario(), params.getClave())){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, 1);
		
			JSONObject jsonPayLoad = new JSONObject();
			jsonPayLoad.put("iss", request.getHeader("origin"));
			jsonPayLoad.put("sub", params.getUsuario());
			jsonPayLoad.put("iat", System.currentTimeMillis());
			jsonPayLoad.put("exp", cal.getTimeInMillis());
		
			// Create JWS payload
			Payload payload = new Payload(jsonPayLoad);
	
			// Create JWS header with HS256 algorithm
			JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
	
			// Create JWS object
			JWSObject jwsObject = new JWSObject(header, payload);
	
			// Create HMAC signer
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
			
			res.put("token", token);
			
			return Response
					.ok()
					.entity(res)
					.build();
		}
		else{
			res.put("message:", "access denied");
			return Response
					.status(Status.UNAUTHORIZED)
					.entity(res)
					.build();
		}
	}
}