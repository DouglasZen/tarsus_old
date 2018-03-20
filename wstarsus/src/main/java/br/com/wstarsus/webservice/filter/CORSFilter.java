package br.com.wstarsus.webservice.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import br.com.wstarsus.webservice.jwt.Public;


@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class CORSFilter implements ContainerResponseFilter{
	
	
	@Override
	public void filter(ContainerRequestContext containerRequest, ContainerResponseContext containerResponse) throws IOException {
		containerResponse.getHeaders().add("Access-Control-Allow-Origin", "*");
		containerResponse.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		containerResponse.getHeaders().add("Access-Control-Allow-Credentials", "true");
		containerResponse.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		containerResponse.getHeaders().add("Access-Control-Max-Age", "1209600");
	}

}
