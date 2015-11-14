package py.com.xsys.restprueba.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/saludar")
@Produces(MediaType.APPLICATION_JSON)
public class ResourceRESTService {
	
	@GET
	@Path("/{param}") 
	public Response getSaludo(@PathParam("param") String param) {
		
		Map<String, String> resultado = new HashMap<String, String>();
		resultado.put("hola", param);
		
		return Response.ok().entity(resultado).build();
	}
}
