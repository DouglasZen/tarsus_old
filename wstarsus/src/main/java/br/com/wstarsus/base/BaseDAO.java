package br.com.wstarsus.base;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDAO {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
	private EntityManager em = factory.createEntityManager();
	
	public EntityManager getEntityManager(){
		return em;
	}
}
