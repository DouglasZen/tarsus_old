package br.com.wstarsus.webservice.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import br.com.wstarsus.usuario.Usuario;
import br.com.wstarsus.usuario.UsuarioDAO;
import br.com.wstarsus.util.Token;
import br.com.wstarsus.webservice.exception.UnauthenticatedException;
import br.com.wstarsus.webservice.jwt.Public;
import br.com.wstarsus.webservice.jwt.Secured;
import br.com.wstarsus.webservice.jwt.TokenJWTUtil;

@Path("/login")
public class LoginResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response autenticarUsuario(Usuario usuario){
		usuario = validarCredenciais(usuario.getEmail(), usuario.getSenha());
		String token = "Bearer " + TokenJWTUtil.gerarToken(usuario.getEmail());
		return Response.ok()
					   .entity(new Token(token, usuario.getEmail(), usuario.getNome(), usuario.getSexo()))
				       .build();
		//return Response.ok().header("Authorization", token).build();
	}

	private Usuario validarCredenciais(String email, String senha) {
		//verificar usuario logado
		
		Usuario usuario = new UsuarioDAO().login(email, senha);
		
		if(usuario == null){
			throw new UnauthenticatedException("Usuário não autenticado");
		}
		return usuario;
	}
}
