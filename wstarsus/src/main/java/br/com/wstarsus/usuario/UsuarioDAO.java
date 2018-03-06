package br.com.wstarsus.usuario;

import javax.persistence.EntityManager;

import br.com.wstarsus.base.BaseDAO;

public class UsuarioDAO extends BaseDAO{
	EntityManager em = getEntityManager();
	
	public void setUsuario(Usuario usuario){
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
	}
}
