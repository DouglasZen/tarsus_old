package br.com.wstarsus.login;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserLogged {
	private String usuario;
	private String token;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
