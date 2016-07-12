package br.com.betorolim.loja.Bean;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.betorolim.loja.Dao.LivroDao;
import br.com.betorolim.loja.Modelo.Livro;

@Model
public class LivroBean {
	
	private Livro livro = new Livro();

	@Inject
	private LivroDao dao;
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public String cadastrar() {
		dao.adiciona(livro);
		return "principal?faces-redirect=true";
	}
	
}
