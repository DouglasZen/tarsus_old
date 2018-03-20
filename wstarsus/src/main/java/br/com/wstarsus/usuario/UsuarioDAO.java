package br.com.wstarsus.usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import br.com.wstarsus.base.BaseDAO;

public class UsuarioDAO extends BaseDAO{
	EntityManager em = getEntityManager();
	
	public void setUsuario(Usuario usuario){
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
	}
	
	public Usuario login(String email, String senha){
		Query query = em.createQuery("select u from Usuario u where u.email = :email and u.senha = :senha", Usuario.class);
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		if(!query.getResultList().isEmpty()){
			Usuario usuario = (Usuario) query.getSingleResult();
			return usuario;
		}else{
			return null;
		}
	}
	
	public boolean isEmailCadastrado(String email){
		Query query = em.createQuery("select u from Usuario u where u.email = :email", Usuario.class);
		query.setParameter("email", email);
		if(!query.getResultList().isEmpty()){
			return true;
		}else{
			return false;
		}
	}
}
