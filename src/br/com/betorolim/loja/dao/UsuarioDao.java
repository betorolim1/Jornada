package br.com.betorolim.loja.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.betorolim.loja.modelo.Usuario;

@Named
@Dependent
public class UsuarioDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public void adiciona(Usuario usuario) {
		manager.getTransaction().begin();
		this.manager.persist(usuario);
		manager.getTransaction().commit();
	}

	public Usuario existePorNome(String login) {
		Query busca = manager.createQuery("select usuario from Usuario usuario where " + "usuario.login = :login")
				.setParameter("login", login);
		try{
			return (Usuario) busca.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	public boolean existePorNomeObject(Object arg2) {
		Query busca = manager.createQuery("select usuario from Usuario usuario where " + "usuario.login = :login")
				.setParameter("login", arg2);
		boolean encontrado = !busca.getResultList().isEmpty();
		return encontrado;
	}

	public Usuario existePorEmail(String email) {
		Query busca = manager.createQuery("select usuario from Usuario usuario where " + "usuario.email = :email")
				.setParameter("email", email);
		try{
			return (Usuario) busca.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	public Usuario buscaUsuario(Usuario usuario) {
		Query busca = manager
				.createQuery("select usuario from Usuario usuario where usuario.login = :login"
						+ " and usuario.senha = :senha")
				.setParameter("login", usuario.getLogin()).setParameter("senha", usuario.getSenha());
		try {
			return (Usuario) busca.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Usuario buscaPorLogin(String login) {
		return manager.find(Usuario.class, login);
	}

	public List<Usuario> listaTodos() {
		String jpql = "select u from Usuario u";
		return manager.createQuery(jpql, Usuario.class).getResultList();
	}

	public void atualiza(Usuario usuario) {
		manager.getTransaction().begin();
		this.manager.merge(usuario);
		manager.getTransaction().commit();
	}

	public void remove(Usuario usuario) {
		manager.getTransaction().begin();
		this.manager.remove(usuario);
		manager.getTransaction().commit();
	}

	public boolean existePorEmailObject(Object arg2) {
		Query busca = manager.createQuery("select usuario from Usuario usuario where " + "usuario.email = :email")
				.setParameter("email", arg2);
		boolean encontrado = !busca.getResultList().isEmpty();
		return encontrado;
	}

}
