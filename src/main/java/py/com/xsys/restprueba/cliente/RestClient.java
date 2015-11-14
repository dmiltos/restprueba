package py.com.xsys.restprueba.cliente;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestClient {
	
	public static final String URL = "http://192.168.1.139:8080/restprueba/rest/productos";
	
	public static void main(String[] args) {
		try {
			Client client = Client.create();
		
			WebResource webResource = client.resource(URL);
		
			ClientResponse response = webResource
				.accept("application/json")
	            .get(ClientResponse.class);
		
			if (response.getStatus() != 200) {
			   
			}
			else {
				String responseString = response.getEntity(String.class);
				
				JSONArray productos = null;
				try {
					JSONParser parser = new JSONParser();
					JSONObject obj = (JSONObject) parser.parse(responseString);
					
					productos = (JSONArray) obj.get("productos");
				
					for (int i=0;i<productos.size(); i++){
						
						JSONObject item = (JSONObject) productos.get(i);
						
						Long id = (Long) item.get("id");
						String fabricante = (String) item.get("fabricante");
						String modelo = (String) item.get("modelo");
						JSONObject usuario = (JSONObject) item.get("usuario");
						Object nombre = usuario.get("usuario");
						Object apellido = usuario.get("apellido");
						JSONArray funcionalidades = (JSONArray) item.get("funcionalidades");
						
						System.out.println("id: " + id);
						System.out.println("fabricante: " + fabricante);
						System.out.println("modelo: " + modelo);						
						System.out.println("usuario: " + nombre + " " + apellido);
						System.out.println("funcionalidades: " + funcionalidades);
						System.out.println("======================================");
					}

				
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	


}
