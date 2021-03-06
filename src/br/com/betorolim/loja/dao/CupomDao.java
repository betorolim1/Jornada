package br.com.betorolim.loja.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.betorolim.loja.modelo.Cupom;

public class CupomDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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

	public Cupom existeCodigo(String codigo) {
		Query busca = manager.createQuery("select cupom from Cupom cupom where cupom.codigo = :codigo")
				.setParameter("codigo", codigo);
		try {
			return (Cupom) busca.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
