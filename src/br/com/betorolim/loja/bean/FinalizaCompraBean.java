package br.com.betorolim.loja.bean;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class FinalizaCompraBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long numeroDoCartao;
	
	private Integer cvv;
	
	private Date DataValidade;
	
	private String endereco;

	public Long getNumeroDoCartao() {
		return numeroDoCartao;
	}

	public void setNumeroDoCartao(Long numeroDoCartao) {
		this.numeroDoCartao = numeroDoCartao;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public Date getDataValidade() {
		return DataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		DataValidade = dataValidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public void finalizaCompra() {
		FacesMessage msg = new FacesMessage("Compra finalizada", "Obrigado!!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
