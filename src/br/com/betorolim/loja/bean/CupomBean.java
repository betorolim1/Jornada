package br.com.betorolim.loja.bean;

import java.io.Serializable;
import java.util.List;

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
	
	private List<Cupom> cupons;
	
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

	public List<Cupom> getCupons() {
		if(cupons == null){
			cupons = dao.listaTodos();
		}
		return cupons;
	}

	public void setCupons(List<Cupom> cupons) {
		this.cupons = cupons;
	}
	
	public void remove(Cupom cupom) {
		dao.remove(cupom);
		cupons = dao.listaTodos();
	}

}
