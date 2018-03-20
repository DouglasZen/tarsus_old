package br.com.wstarsus.webservice.jwt;

import java.security.Principal;

public class UserDetails implements Principal{
	private final String email;
	
	public UserDetails(String email){
		this.email = email;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return email;
	}

}
