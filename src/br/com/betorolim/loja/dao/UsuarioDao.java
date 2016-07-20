package br.com.betorolim.loja.dao;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.betorolim.loja.modelo.Usuario;

@Named
@Dependent
public class UsuarioDao {

	
	@Inject
	private EntityManager manager;

	public void adiciona(Usuario usuario) {
		manager.getTransaction().begin();
		this.manager.persist(usuario);
		manager.getTransaction().commit();
	}
	
	public boolean existePorNome(Usuario usuario) {
		Query busca = manager.createQuery("select usuario from Usuario usuario where usuario.login = :login")
				.setParameter("login", usuario.getLogin());
		boolean encontrado = !busca.getResultList().isEmpty();
		return encontrado;
	}
	
	public boolean existePorEmail(Usuario usuario) {
		Query busca = manager.createQuery("select usuario from Usuario usuario where usuario.email = :email")
				.setParameter("email", usuario.getEmail());
		boolean encontrado = !busca.getResultList().isEmpty();
		return encontrado;
	}
	
	public boolean existe(Usuario usuario) {
		Query busca = manager.createQuery("select usuario from Usuario usuario where usuario.login = :login"
				+ " and usuario.senha = :senha").setParameter("login", usuario.getLogin())
				.setParameter("senha", usuario.getSenha());
		boolean encontrado = !busca.getResultList().isEmpty();
		return encontrado;
	}
	
	public Usuario buscaPorLogin(String login) {
		return manager.find(Usuario.class, login);
	}

}
