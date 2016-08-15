package br.com.betorolim.loja.bean;

import java.io.IOException;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import br.com.betorolim.loja.dao.UsuarioDao;
import br.com.betorolim.loja.modelo.Usuario;

@Model
public class UsuarioBean {

	private Usuario usuario = new Usuario();

	private List<Usuario> usuarios;

	@Inject
	private UsuarioDao dao;

	@Inject
	private LoginBean loginBean;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void cadastrar() throws IOException {
		Usuario usuarioEncontradoLogin = dao.existePorNome(usuario.getLogin());
		Usuario usuarioEncontradoEmail = dao.existePorEmail(usuario.getEmail());
		boolean cadastra = true;
		if (usuarioEncontradoLogin != null && !usuarioEncontradoLogin.equals(usuario)) {
			cadastra = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login ja existe!", null));
		}

		if (usuarioEncontradoEmail != null && !usuarioEncontradoEmail.equals(usuario)) {
			cadastra = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "E-mail ja existe!", null));
		}

		if (cadastra == true) {
			loginBean.setUsuario(usuario);
			dao.adiciona(usuario);
			FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
		}
	}

	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			usuarios = dao.listaTodos();
		}
		return usuarios;
	}

	public void remove(Usuario usuario) {
		dao.remove(usuario);
		this.usuarios = dao.listaTodos();
	}

	public void editaLinha(RowEditEvent event) throws IOException {
		usuario = (Usuario) event.getObject();
		Usuario usuarioEncontradoLogin = dao.existePorNome(usuario.getLogin());
		Usuario usuarioEncontradoEmail = dao.existePorEmail(usuario.getEmail());
		boolean atualiza = true;
		if (usuarioEncontradoLogin != null && !usuarioEncontradoLogin.equals(usuario)) {
			atualiza = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login ja existe!", null));
		}

		if (usuarioEncontradoEmail != null && !usuarioEncontradoEmail.equals(usuario)) {
			atualiza = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "E-mail ja existe!", null));
		}

		if (atualiza == true) {
			dao.atualiza(usuario);
			FacesMessage msg = new FacesMessage("Usuario atualizado", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		usuarios = dao.listaTodos();
	}

	public void cancelaEdicao(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição cancelada", null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

}
