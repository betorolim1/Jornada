package br.com.betorolim.loja.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.betorolim.loja.dao.CupomDao;
import br.com.betorolim.loja.modelo.Cupom;

@Named
@RequestScoped
public class CupomBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Cupom cupom = new Cupom();
	
	@Inject
	private CupomDao dao;

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}
	
	public String cadastrar() {
		dao.adiciona(cupom);
		return "admin?faces-redirect=true";
	}

}
