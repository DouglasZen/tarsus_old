package br.com.wstarsus.webservice.resource;




import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.annotation.JSONP;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.wstarsus.place.Place;
import br.com.wstarsus.place.Places;
import br.com.wstarsus.webservice.jwt.Secured;

@Path("/locais")
public class LocalResource {
	@Secured
	@GET
	@Path("{local}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocal(@PathParam("local") String local) throws JsonParseException, JsonMappingException, IOException{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://maps.googleapis.com/maps/api/place/nearbysearch/json?radius=100&types=establishment&key=AIzaSyD2I-qhJUytV9N_xfXUBjBgEbuQ72BJmhM&location=" + local);
		
		Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
		Response response = invocation.get();
		String placeS = response.readEntity(String.class);
		
		
		Place place = new Gson().fromJson(placeS, Place.class);
		List<Places> p = place.getResults();
		Iterator<Places> i = p.iterator();
		while(i.hasNext()){
			List types = Arrays.asList(i.next().getTypes());
			if(((!types.contains("night_club")) && (!types.contains("bar"))) && (!(types.contains("night_club") && (!types.contains("bar"))))){
				i.remove();
			}
			
		}
		return Response.ok().entity(place).build();
	}
}
