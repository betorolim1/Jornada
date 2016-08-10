package br.com.betorolim.loja.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.betorolim.loja.modelo.Livro;

@Named
@SessionScoped
public class CarrinhoDeComprasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginBean usuarioLogado = new LoginBean();

	private Double total = 0.0;

	private List<Livro> livros = new ArrayList<Livro>(); 

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

	public void adicionaEbook(Livro livro) throws IOException {
//		System.out.println(usuarioLogado.getUsuario());
//		if(usuarioLogado.getUsuario()!=null){
			livro.setTipoComprado("Ebook");
			livros.add(livro);
			total += livro.getPrecoEbook();
			
			FacesMessage msg = new FacesMessage("Livro adicionado",null);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
//		}else{
//			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Faça login para comprar",null);
//	        FacesContext.getCurrentInstance().addMessage(null, msg);
//		}
	}

	public String adicionaImpresso(Livro livro) {
		livro.setTipoComprado("Impresso");
		livros.add(livro);
		total += livro.getPrecoImpresso();
		return "principal?faces-redirect=true";
	}

	public String adicionaCombo(Livro livro) {
		livro.setTipoComprado("Combo");
		livros.add(livro);
		total += livro.getPrecoCombo();
		return "principal?faces-redirect=true";
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
}
