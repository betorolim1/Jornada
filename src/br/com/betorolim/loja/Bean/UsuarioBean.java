package br.com.betorolim.loja.Bean;
import java.io.IOException;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

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
	
	public boolean isLogado() {
		if (usuario.getLogin() == null) {
			return false;
		}else{
			return true;
		}
	}
	
	public void menuLogin() throws IOException {
		if(this.isLogado()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario já logado", null));
		}else{
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		}
	}
	
	public void menuCarrinho() {
		
	}
	
	public void menuLogout() throws IOException {
		if(this.isLogado()){
			this.usuario = new Usuario();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario não está logado", null));
		}
		
	}

	public void cadastrar() throws IOException{
		//String login = usuario.getLogin();
		if(!dao.existePorNome(usuario)){
				dao.adiciona(usuario);
				FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login ja utilizado", null));
		}
		//this.usuario = new Usuario();
		//this.usuario = dao.buscaPorLogin(login);
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
