package br.com.betorolim.loja.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.betorolim.loja.modelo.Compra;

public class CompraDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public void adiciona(Compra compra){
		manager.getTransaction().begin();
		manager.persist(compra);
		manager.getTransaction().commit();
	}

}
