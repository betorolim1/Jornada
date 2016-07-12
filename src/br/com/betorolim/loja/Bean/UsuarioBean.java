package br.com.betorolim.loja.Bean;
import java.io.IOException;


import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.betorolim.loja.Dao.UsuarioDao;
import br.com.betorolim.loja.Modelo.Usuario;

//@Model //@RequestScoped e @Named
@ManagedBean
@SessionScoped
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	
	@Inject
	private UsuarioDao dao;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String cadastrar(){
		//String login = usuario.getNome();
		dao.adiciona(usuario);
		//this.usuario = new Usuario();
		//this.usuario = dao.buscaPorLogin(login);
		return "principal?faces-redirect=true";
	}
	
	public void login() throws IOException{
		boolean login = dao.existe(usuario);
		System.out.println(login);
		if(login == true){
			FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
		}else{ 
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login Invalido", null));
			this.usuario = new Usuario();
		}
	}
	
}
