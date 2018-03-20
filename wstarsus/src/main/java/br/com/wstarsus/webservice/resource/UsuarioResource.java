package br.com.wstarsus.webservice.resource;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.wstarsus.usuario.Usuario;
import br.com.wstarsus.usuario.UsuarioDAO;
import br.com.wstarsus.util.Token;
import br.com.wstarsus.webservice.jwt.TokenJWTUtil;

@Path("/usuario")
public class UsuarioResource {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Usuario usuario){
		try{
			new UsuarioDAO().setUsuario(usuario);
			String token = "Bearer " + TokenJWTUtil.gerarToken(usuario.getEmail());
			return  Response.status(Status.CREATED).entity(new Token(token, usuario.getEmail(), usuario.getNome(), usuario.getSexo())).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Path("/email")
	public Response verificaEmail(String email){
		try{
			boolean isEmail = new UsuarioDAO().isEmailCadastrado(email);
			if(isEmail){
				return Response.status(Status.CONFLICT).entity("email encontrado").build();
			}else{
				return Response.status(Status.NO_CONTENT).entity("email nao encontrado").build();
			}
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
