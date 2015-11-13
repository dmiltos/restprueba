package py.com.xsys.restprueba.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/decirhola")
@Produces(MediaType.APPLICATION_JSON)
public class ResourceRESTtService {
	
	@GET
	@Path("/{param}") 
	public Response getDatos(@PathParam("param") String param) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("Hola: ", param);
		
		return Response.ok().entity(map).build();
	}
}
