package py.com.xsys.restprueba.cliente;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestClient {
	
//Cambiar por la url que provee la informacion
//	public static final String URL = "http://localhost:8080/restproductos/rest/productos";
	public static final String URL = "http://www.google.com";
	
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
				//Descomentar cuando se reciben los datos de una url, y comentar datos de prueba 
//				String responseString = response.getEntity(String.class);
				String responseString = "{\"productos\":[{\"id\":100,\"fabricante\":\"google\",\"modelo\":\"Nexus 4\",\"red\":\"LTE\",\"usuario\":{\"nombre\":\"Derlis\",\"apellido\":\"Miltos\"},\"funcionalidades\":[\"GPS\",\"Touch\",\"NFC\"]},{\"id\":200,\"fabricante\":\"samsung\",\"modelo\":\"Galaxy S5\",\"red\":\"GSM\",\"usuario\":{\"nombre\":\"Anibal\",\"apellido\":\"Dure\"},\"funcionalidades\":[\"GPS\",\"Touch\"]},{\"id\":300,\"fabricante\":\"sony\",\"modelo\":\"xperia\",\"red\":null,\"usuario\":{\"nombre\":\"Pablo\",\"apellido\":\"Rodriguez\"},\"funcionalidades\":[]},{\"id\":400,\"fabricante\":\"amazon\",\"modelo\":\"firephone\",\"red\":null,\"usuario\":{\"nombre\":\"Xsys\",\"apellido\":\"Capactiacion y Tecnologia\"},\"funcionalidades\":[]}]}";
				
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
