package br.com.betorolim.loja.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

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

	public void cadastrar() throws IOException {
		Cupom cupomEncontrado = dao.existeCodigo(cupom.getCodigo());
		if (cupomEncontrado != null && !cupomEncontrado.equals(cupom)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Código ja existe!", null));
		} else {
			dao.adiciona(cupom);
			FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
		}
	}

	public List<Cupom> getCupons() {
		if (cupons == null) {
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

	public void editaLinha(RowEditEvent event) throws IOException {
		cupom = (Cupom) event.getObject();
		Cupom cupomEncontrado = dao.existeCodigo(cupom.getCodigo());
		if (cupomEncontrado != null && !cupomEncontrado.equals(cupom)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Código ja existe!", null));
		} else {
			dao.atualiza(cupom);
			FacesMessage msg = new FacesMessage("Cupom atualizado", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		cupons = dao.listaTodos();
	}

	public void cancelaEdicao(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição cancelada", null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
