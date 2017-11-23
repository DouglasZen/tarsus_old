package br.com.wstarsus.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.wstarsus.place.Place;

@Path("/locais")
public class LocalResource {
	@GET
	@Path("{local}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocal(@PathParam("local") String local){
		Client client = Client.create();
		WebResource wr = client.resource("https://maps.googleapis.com/maps/api/place/nearbysearch/json?radius=250&types=bar&name=&key=AIzaSyD2I-qhJUytV9N_xfXUBjBgEbuQ72BJmhM&location=" + local);
		ClientResponse r = wr.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		Place teste = r.getEntity(Place.class);
		return Response.ok().entity(teste).build();
	}
}
