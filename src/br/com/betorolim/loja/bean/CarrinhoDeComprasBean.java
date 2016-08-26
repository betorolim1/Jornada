package br.com.betorolim.loja.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.betorolim.loja.dao.CupomDao;
import br.com.betorolim.loja.modelo.Cupom;
import br.com.betorolim.loja.modelo.FinalizaCompra;
import br.com.betorolim.loja.modelo.Formato;
import br.com.betorolim.loja.modelo.Item;
import br.com.betorolim.loja.modelo.Livro;

@Named
@SessionScoped
public class CarrinhoDeComprasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double total = 0.0;

	private List<Item> itens = new ArrayList<Item>();
	
	private Item item;

	private FinalizaCompra compra = new FinalizaCompra();

	private String codigo;

	@Inject
	private CupomDao cupomDao;

	boolean cupomValidado = false;

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void adicionaEbook(Livro livro) throws IOException, InterruptedException {
		item = new Item();
		boolean mesmoLivro = false;
		
		item.setLivro(livro);
		item.setFormato(Formato.Ebook);
		
		for (Item item1 : itens) {
			if(item1.equals(item)){
				item1.setQuantidade(item1.getQuantidade() + 1);
				mesmoLivro = true;
			}
		}
		if(!mesmoLivro){
			itens.add(item);
		}
		
		total += livro.getPrecoEbook();
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Ebook adicionado ao carrinho!", null));
		FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
	}

	public void adicionaImpresso(Livro livro) throws IOException {
		item = new Item();
		boolean mesmoLivro = false;
		
		item.setLivro(livro);
		item.setFormato(Formato.Impresso);
		
		for (Item item1 : itens) {
			if(item1.equals(item)){
				item1.setQuantidade(item1.getQuantidade() + 1);
				mesmoLivro = true;
			}
		}
		if(!mesmoLivro){
			itens.add(item);
		}
		total += livro.getPrecoImpresso();
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Impresso adicionado ao carrinho!", null));
		FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
	}

	public void adicionaCombo(Livro livro) throws IOException {
		item = new Item();
		boolean mesmoLivro = false;
		
		item.setLivro(livro);
		item.setFormato(Formato.Combo);
		
		for (Item item1 : itens) {
			if(item1.equals(item)){
				item1.setQuantidade(item1.getQuantidade() + 1);
				mesmoLivro = true;
			}
		}
		if(!mesmoLivro){
			itens.add(item);
		}
		total += livro.getPrecoCombo();
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Combo adicionado ao carrinho!", null));
		FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
	}

	public void remove(Item item) {
		if(item.getQuantidade() == 1){
			itens.remove(item);
		}else{
			item.setQuantidade(item.getQuantidade() - 1);
		}
		switch (item.getFormato()) {
		case Ebook:
			total -= item.getLivro().getPrecoEbook();
			break;
		case Impresso:
			total -= item.getLivro().getPrecoImpresso();
			break;
		case Combo:
			total -= item.getLivro().getPrecoCombo();
			break;
		}
	}

	public void finalizaCompra() throws IOException {
		if (total == 0.0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Compra inválida!", null));
		} else {
			itens.clear();
			total = 0.0;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra finalizada com sucesso!", null));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
		}
	}

	public FinalizaCompra getCompra() {
		return compra;
	}

	public void setCompra(FinalizaCompra compra) {
		this.compra = compra;
	}

	public void validaCupom() {
		Cupom cupomEncontrado = cupomDao.existeCodigo(codigo);

		Date dataAtual = new Date(System.currentTimeMillis());

		if (cupomEncontrado != null && cupomValidado == false) {
			if (dataAtual.before(cupomEncontrado.getDataValidade())) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Cupom registrado com sucesso!", null));
				total = total * (cupomEncontrado.getDesconto() / 100.0);
				cupomValidado = true;
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "Cupom expirado!", null));
			}
		} else if (cupomEncontrado == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Cupom não existe!", null));
		} else if (cupomValidado == true) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Cupom já validado!", null));
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public boolean isEmptyItens() {
		return itens.isEmpty();
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
}
