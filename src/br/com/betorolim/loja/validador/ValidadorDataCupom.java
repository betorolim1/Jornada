package br.com.betorolim.loja.validador;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value= "ValidadorDataCupom")
public class ValidadorDataCupom implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		Date dataAtual = new Date(System.currentTimeMillis());
		if(dataAtual.before((Date) arg2)){
			
		}else{
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_FATAL,"Data Invalida",null));
		}
	}
	
}
