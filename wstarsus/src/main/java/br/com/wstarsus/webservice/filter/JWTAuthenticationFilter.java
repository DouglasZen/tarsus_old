package br.com.wstarsus.webservice.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Key;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.hibernate.annotations.common.reflection.ReflectionUtil;

import br.com.wstarsus.webservice.exception.UnauthenticatedException;
import br.com.wstarsus.webservice.jwt.JWTSecurityContext;
import br.com.wstarsus.webservice.jwt.KeyGenerator;
import br.com.wstarsus.webservice.jwt.Public;
import br.com.wstarsus.webservice.jwt.Secured;
import br.com.wstarsus.webservice.jwt.TokenJWTUtil;
import br.com.wstarsus.webservice.jwt.UserDetails;


import javax.ws.rs.Priorities;



@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTAuthenticationFilter implements ContainerRequestFilter{
	
	private KeyGenerator keyGenerator = new KeyGenerator();
	
	@Context
	private ResourceInfo ri;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if(authorizationHeader != null && authorizationHeader.contains("Bearer ")){
			String token = authorizationHeader.substring("Bearer".length()).trim();
			Key key = keyGenerator.generatedKey();
			
			if(TokenJWTUtil.tokenValido(token, key)){
				String email = TokenJWTUtil.recuperarEmail(token, key);
				UserDetails userdetails = new UserDetails(email);
				
				boolean secure = requestContext.getSecurityContext().isSecure();
				requestContext.setSecurityContext(new JWTSecurityContext(userdetails, secure));
				return;
			}
		}
		throw new UnauthenticatedException("Token inválido/expirado ou usuário não autenticado!");
	}
	
	private boolean verificarPublic(ContainerRequestContext requestContext){
		System.out.println(ri.getResourceMethod().getName());
		Method method = ri.getResourceMethod();
		if(method.isAnnotationPresent(Public.class)){
			return true;
		}
		return false;
	}
	
}
