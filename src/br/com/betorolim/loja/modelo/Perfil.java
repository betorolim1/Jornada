package br.com.betorolim.loja.modelo;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EnumType;

@FacesConverter("enumTypeConverter")
public enum Perfil implements Converter {
    Padrao("Padrao"),
    Administrador("Administrador");
	
	private String label;
	
	Perfil(String label){
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if(arg2 != null){
			return EnumType.valueOf(arg2);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2 != null && arg2 instanceof EnumType){
			return ((EnumType) arg2).name();
		}
		return null;
	}
}
