package br.com.betorolim.loja.validador;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.betorolim.loja.modelo.Cupom;

public class ValidadorDataCupom {

	public boolean verificaData(Cupom cupom) {
		Date dataAtual = new Date(System.currentTimeMillis());
		if(dataAtual.before(cupom.getDataValidade())){
			return true;
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Data Invalida", null));
			return false;
		}
	}
	
}
