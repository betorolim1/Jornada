package br.com.betorolim.loja.dao;

import java.util.List;

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

	public List<Cupom> listaTodos() {
		String jpql = "select c from Cupom c";
		return manager.createQuery(jpql, Cupom.class).getResultList();
	}

	public void remove(Cupom cupom) {
		manager.getTransaction().begin();
		manager.remove(manager.merge(cupom));
		manager.getTransaction().commit();
	}

	public void atualiza(Cupom cupom) {
		manager.getTransaction().begin();
		manager.merge(cupom);
		manager.getTransaction().commit();
	}

}
