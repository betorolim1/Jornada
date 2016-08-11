package br.com.betorolim.loja.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.betorolim.loja.modelo.FinalizaCompra;
import br.com.betorolim.loja.modelo.Livro;

@Named
@SessionScoped
public class CarrinhoDeComprasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double total = 0.0;

	private List<Livro> livros = new ArrayList<Livro>();

	@Inject
	private FinalizaCompra compra;

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public void adicionaEbook(Livro livro) throws IOException, InterruptedException {
		livro.setTipoComprado("Ebook");
		livros.add(livro);
		total += livro.getPrecoEbook();
		FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
	}

	public void adicionaImpresso(Livro livro) throws IOException {
		livro.setTipoComprado("Impresso");
		livros.add(livro);
		total += livro.getPrecoImpresso();
		FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
	}

	public void adicionaCombo(Livro livro) throws IOException {
		livro.setTipoComprado("Combo");
		livros.add(livro);
		total += livro.getPrecoCombo();
		FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
	}

	public void remove(Livro livro) {
		livros.remove(livro);
		switch (livro.getTipoComprado()) {
		case "Ebook":
			total -= livro.getPrecoEbook();
			System.out.println("E");
			break;
		case "Impresso":
			total -= livro.getPrecoImpresso();
			System.out.println("I");
			break;
		case "Combo":
			total -= livro.getPrecoCombo();
			System.out.println("C");
			break;
		}
	}

	public void finalizaCompra() throws IOException {
		livros.clear();
		total = 0.0;

		FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
	}

	public FinalizaCompra getCompra() {
		return compra;
	}

	public void setCompra(FinalizaCompra compra) {
		this.compra = compra;
	}
}
