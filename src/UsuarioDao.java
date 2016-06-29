import javax.inject.Inject;
import javax.persistence.EntityManager;

public class UsuarioDao {
	
	@Inject
	private EntityManager manager;

	public void adiciona(Usuario usuario) {
		this.manager.persist(usuario);
	}

}
