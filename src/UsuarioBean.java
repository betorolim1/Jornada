import javax.enterprise.inject.Model;
import javax.inject.Inject;

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

	public void cadastrar(){
		dao.adiciona(usuario);
	}
	
}
