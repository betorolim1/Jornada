package br.com.betorolim.loja.Dao;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.betorolim.loja.Modelo.Livro;

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
