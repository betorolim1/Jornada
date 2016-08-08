package br.com.betorolim.loja.dao;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

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

	public Livro buscaLivroPorTitulo(String titulo) {
		Query query = manager.createQuery("select livro from Livro livro where titulo = :titulo)")
				.setParameter("titulo", titulo);
		try{
			return (Livro) query.getSingleResult();
		}catch (NoResultException e){
			return null;
		}
		
	}

	public boolean existePorTitulo(Object arg2) {
		Query busca = manager.createQuery("select livro from Livro livro where " + "livro.titulo = :titulo")
				.setParameter("titulo", arg2);
		boolean encontrado = !busca.getResultList().isEmpty();
		return encontrado;
	}
	
}
