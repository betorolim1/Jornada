package Dao;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import Modelo.Livro;

@Named
@Dependent
public class LivroDao {

	@Inject
	private EntityManager manager;
	
	public void adiciona(Livro livro) {
		manager.getTransaction().begin();
		manager.persist(livro);
		manager.getTransaction().commit();
	}
	
}
