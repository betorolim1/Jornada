package br.com.betorolim.loja.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.betorolim.loja.modelo.Cupom;

public class CupomDao {
	
	@Inject
	private EntityManager manager;

	public void adiciona(Cupom cupom) {
		manager.getTransaction().begin();
		manager.persist(cupom);
		manager.getTransaction().commit();
	}

}
