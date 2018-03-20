package br.com.wstarsus.util;

public class Token {
	private String token;
	private String email;
	private String nome;
	private String sexo;
	
	public Token(String token, String email, String nome, String sexo) {
		super();
		this.token = token;
		this.email = email;
		this.nome = nome;
		this.sexo = sexo;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
}
