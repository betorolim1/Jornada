package br.com.betorolim.loja.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.betorolim.loja.dao.UsuarioDao;
import br.com.betorolim.loja.modelo.Usuario;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;

	@Inject
	private UsuarioDao dao;
	
	private CarrinhoDeComprasBean carrinho = new CarrinhoDeComprasBean();
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public boolean isLogado() {
		if (usuario.getLogin() == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void login() throws IOException {
		Usuario encontrado = dao.buscaUsuario(usuario);
		if (encontrado != null) {
			this.usuario = encontrado;
			carrinho.setUsuario(usuario);
			FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login Invalido", null));
			this.usuario = new Usuario();
		}
	}
	
	public void menuLogin() throws IOException {
		if (this.isLogado()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario já logado", null));
		} else {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		}
	}

	public void menuCarrinho() {

	}
	
	public void menuLogout() throws IOException {
		if (this.isLogado()) {
			this.usuario = new Usuario();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario não está logado", null));
		}

	}
}
