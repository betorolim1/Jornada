package br.com.betorolim.loja.bean;

import java.io.IOException;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.primefaces.event.RowEditEvent;

import br.com.betorolim.loja.dao.UsuarioDao;
import br.com.betorolim.loja.modelo.Perfil;
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
		try {
			if (!dao.existePorNome(usuario)) {
				if (!dao.existePorEmail(usuario)) {
					loginBean.setUsuario(usuario);
					dao.adiciona(usuario);
					FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_FATAL, "E-mail ja utilizado", null));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login ja utilizado", null));
			}
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Não autorizado", null));
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
		boolean erro = false;
		usuario = (Usuario) event.getObject();
		if (dao.existePorNome(usuario)) {
			erro = true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "E-mail ja utilizado", null));
		}
		if (dao.existePorEmail(usuario)) {
			erro = true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Login ja utilizado", null));
		}
		if (erro == false) {
			FacesMessage msg = new FacesMessage("Usuario atualizado", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			dao.atualiza(usuario);
			usuarios = dao.listaTodos();
		}
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

	public Perfil[] getPerfil() {
		return Perfil.values();
	}

}
