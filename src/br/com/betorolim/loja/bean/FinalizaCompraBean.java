package br.com.betorolim.loja.bean;

import java.io.Serializable;

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
	
	
	
	public void finalizaCompra() {
		FacesMessage msg = new FacesMessage("Compra finalizada", "Obrigado!!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
