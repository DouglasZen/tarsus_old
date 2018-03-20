package br.com.wstarsus.place;

import java.io.Serializable;

public class Places implements Serializable{
	private String id;
	private String name;
	private String[] types;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	
	
}
