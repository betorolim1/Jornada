package Bean;
import javax.enterprise.inject.Model;
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
		dao.adiciona(usuario);
		return "principal?faces-redirect=true";
	}
	
	public String login(){
		boolean login = dao.existe(usuario);
		System.out.println(login);
		if(login == true){
			return "principal?faces-redirect=true";
		}else{ 
			this.usuario = new Usuario();
			return "index?faces-redirect=true";
		}
	}
	
}
