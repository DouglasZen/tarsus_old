package br.com.wstarsus.login;

import java.lang.invoke.MethodHandleInfo;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.wstarsus.usuario.Usuario;
import br.com.wstarsus.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
@Path("/login")
public class LoginResource {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Credenciais c){
		Usuario usuario = new LoginDAO().login(c.getUsuario(), c.getPassword());
		
		if(usuario != null){
			String token = JWTUtil.create(c.getUsuario());
			UserLogged me = new UserLogged();
			me.setUsuario(c.getUsuario());
			me.setToken(token);
			return Response.ok().entity(me).build();
		}else{
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public UserLogged me(@Context HttpServletRequest request){
		String token = request.getHeader(JWTUtil.TOKEN_HEADER);
		Jws<Claims> jws = JWTUtil.decode(token);
		UserLogged me = new UserLogged();
		me.setUsuario(jws.getBody().getSubject());
		return me;
	}
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response teste(){
		Credenciais c = new Credenciais();
		c.setPassword("1234");
		c.setUsuario("douglas");
		return Response.ok().entity(c).build();
	}*/
}
