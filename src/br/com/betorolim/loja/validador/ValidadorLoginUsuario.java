package br.com.betorolim.loja.validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import br.com.betorolim.loja.dao.UsuarioDao;

@FacesValidator(value="ValidadorLoginUsuario")
public class ValidadorLoginUsuario implements Validator {
	
	@Inject
	private UsuarioDao dao;

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		if(dao.existePorNomeObject(arg2)){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_FATAL,"Usuario ja existe",null));
		}
		
	}

}
