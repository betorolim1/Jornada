package br.com.betorolim.loja.bean;

import java.io.IOException;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.com.betorolim.loja.dao.UsuarioDao;
import br.com.betorolim.loja.modelo.Usuario;

//@Model //@RequestScoped e @Named
@Model
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

	public void cadastrar() throws IOException {
		try {
			if (!dao.existePorNome(usuario)) {
				if (!dao.existePorEmail(usuario)) {
					dao.adiciona(usuario);
					FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
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

}
