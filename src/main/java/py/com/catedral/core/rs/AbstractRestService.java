package py.com.catedral.core.rs;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.minidev.json.JSONObject;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;

public abstract class AbstractRestService {

	protected String sharedKey = "a0a2abd8616241c383d61cf559b46afc";
	
	protected Response validarAuthHeader(HttpServletRequest request,
			Map<String, String> res) {
		if (request.getHeader("authorization") == null){
			res.put("message", "Header Authorization no encontrado");
			return Response
					.status(Status.UNAUTHORIZED)
					.entity(res)
					.build();
		}
		return null;
	}
	
	protected JSONObject decodePayload(HttpServletRequest request) {
		
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
		//System.out.println("Recovered payload message: " + jwsObject.getPayload());
		
		JSONObject jsonPayLoad = jwsObject.getPayload().toJSONObject();
		return jsonPayLoad;
	}
}
