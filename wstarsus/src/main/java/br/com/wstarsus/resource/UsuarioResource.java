package br.com.wstarsus.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.wstarsus.usuario.Usuario;
import br.com.wstarsus.usuario.UsuarioDAO;

@Path("/usuario")
public class UsuarioResource {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Usuario usuario){
		try{
			new UsuarioDAO().setUsuario(usuario);
			return  Response.status(Status.CREATED).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
