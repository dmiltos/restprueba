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
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/inventariar")
	public Response inventariar(ParametrosInventario params, @Context HttpServletRequest request){
		//Tomamos las credenciales en base64
		Producto prod = null;
		Map<String,String> res = new HashMap<String, String>();
		
		if (request.getHeader("authorization") == null){
			res.put("message", "Header Authorization no encontrado");
			return Response
					.status(Status.UNAUTHORIZED)
					.entity(res)
					.build();
		}
		
		JSONObject jsonPayLoad = decodePayload(request);
		String user = (String) jsonPayLoad.get("sub");
		String pass = (String) jsonPayLoad.get("pss");
		
		try {
			prod = inventarioService.inventariar(params.getCodigoBarras(), 
					params.getCodigoInventario(), params.getIndicadorManual(), "ENPRO", 
					params.getCantidad(), params.getLote(), params.getVencimiento(), "WS", user, pass);
			
		} catch (AppException | BusinessException e) {			
			e.printStackTrace();
		}
		
		if (prod != null){
			return Response.ok(prod).build();
		}
		else{	
			return Response.status(Status.NO_CONTENT).entity(res).build();
		}
	}
	
	@GET
	@Path("/api/me")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response ensureAuthenticated(@Context HttpServletRequest request){
		
		Map<String,String> res = new HashMap<String, String>();
		
		if (request.getHeader("authorization") == null){
			res.put("message", "Header Authorization no encontrado");
			return Response
					.status(Status.UNAUTHORIZED)
					.entity(res)
					.build();
		}
		
		JSONObject jsonPayLoad = decodePayload(request);
		return Response
				.ok()
				.entity(jsonPayLoad)
				.build();
	}

	private JSONObject decodePayload(HttpServletRequest request) {
				
		String token = request.getHeader("authorization");
		token = token.startsWith("Bearer ") ? token.substring(7) : token;
		
		// Parse back and check signature
		JWSObject jwsObject = null;
		try {
			jwsObject = JWSObject.parse(token);
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
		return jsonPayLoad;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/auth")
	public Response login(CredencialesCliente params, @Context HttpServletRequest request){		

		Map<String,String> res = new HashMap<String, String>();
		
		if (inventarioService.login(params.getUsuario(), params.getClave())){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, 1);
		
			JSONObject jsonPayLoad = new JSONObject();
			jsonPayLoad.put("iss", request.getHeader("origin"));
			jsonPayLoad.put("sub", params.getUsuario());
			jsonPayLoad.put("pss", params.getClave());
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
			res.put("message", "Acceso denegado");
			return Response
					.status(Status.UNAUTHORIZED)
					.entity(res)
					.build();
		}
	}
}