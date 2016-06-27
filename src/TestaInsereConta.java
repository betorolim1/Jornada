import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestaInsereConta {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("livraria");
		
		EntityManager manager = factory.createEntityManager();
		
		Usuario usuario = new Usuario();
		usuario.setLogin("Joao");
		usuario.setSenha("joao");
		usuario.setEmail("joao@gmail.com");
		usuario.setPerfil(0);
		
		manager.getTransaction().begin();
		manager.persist(usuario);
		manager.getTransaction().commit();
		manager.close();
		
		System.out.println("Gravado com sucesso");
		
	}
}
