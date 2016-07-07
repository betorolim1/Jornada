package Dao;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

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

}
