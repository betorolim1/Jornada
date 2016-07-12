package Bean;
import java.io.IOException;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import Dao.UsuarioDao;
import Modelo.Usuario;

@Model //@RequestScoped e @Named
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
		Long id = this.usuario.getId();
		dao.adiciona(usuario);
		//this.usuario = new Usuario();
		//this.usuario = dao.buscaPorId(id);
		System.out.println("Id " + id);
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
