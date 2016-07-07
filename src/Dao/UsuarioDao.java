package Dao;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import Modelo.Usuario;

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
	
	public boolean existe(Usuario usuario) {
		Query busca = manager.createQuery("select usuario from Usuario usuario where usuario.nome = :nome"
				+ " and usuario.senha = :senha").setParameter("nome", usuario.getNome())
				.setParameter("senha", usuario.getSenha());
		boolean encontrado = !busca.getResultList().isEmpty();
		return encontrado;
	}

}
