package br.com.betorolim.loja.dao;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.betorolim.loja.modelo.Livro;

@Named
@Dependent
public class LivroDao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public void adiciona(Livro livro) {
		manager.getTransaction().begin();
		manager.persist(livro);
		manager.getTransaction().commit();
	}

	public void atualiza(Livro livro) {
		manager.getTransaction().begin();
		manager.merge(livro);
		manager.getTransaction().commit();
	}

	public List<Livro> listaTodos() {
		String jpql = "select l from Livro l";
		return manager.createQuery(jpql, Livro.class).getResultList();
	}
	
	public void remove(Livro livro) {
		manager.getTransaction().begin();
		manager.remove(manager.merge(livro));
		manager.getTransaction().commit();
	}
	
}
