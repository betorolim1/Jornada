package br.com.betorolim.loja.modelo;

public enum Perfil {
    Padrao("Padrao"),
    Administrador("Administrador");
	
	private String label;
	
	Perfil(String label){
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
