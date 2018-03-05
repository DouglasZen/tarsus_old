package br.com.wstarsus.login;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import br.com.wstarsus.base.BaseDAO;
import br.com.wstarsus.usuario.Usuario;

public class LoginDAO extends BaseDAO{
	private EntityManager em = getEntityManager();
	
	public Usuario login(String nome, String senha){
		Query query = em.createQuery("select u from Usuario u where u.nome = :nome and u.senha = :senha" , Usuario.class);
		query.setParameter("nome", nome);
		query.setParameter("senha", senha);
		if(!query.getResultList().isEmpty()){
			Usuario usuario = (Usuario) query.getSingleResult();
			return usuario;
		}else{
			return new Usuario();
		}
	}
}
