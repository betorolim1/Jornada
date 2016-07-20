package br.com.betorolim.loja.dao;
import java.io.Serializable;

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
	
}
