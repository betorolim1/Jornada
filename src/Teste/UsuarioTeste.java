package Teste;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import Dao.UsuarioDao;
import Modelo.Usuario;

public class UsuarioTeste {
	
	@Inject
	private EntityManager manager;
	
	@Test
	public void deveCadastrar1Usuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Teste");
		usuario.setSenha("teste");
		usuario.setEmail("teste@teste.com");
		usuario.setPerfil(0);
		
		UsuarioDao usuarioDao = new UsuarioDao();
		usuarioDao.adiciona(usuario);
		
		Assert.assertTrue(manager.find(Usuario.class, usuario.getNome()) != null);
	}
}
