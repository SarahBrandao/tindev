package com.soft.tindev.repository;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.soft.tindev.modelo.Usuario;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioRepositoryTest {
	
	
	@Autowired
	private UsuarioRepository repository;

	@Test
	public void testBuscarPeloUsuario() {
		String nomeUsuario = "SarahBrandao";
		Optional<Usuario> usuarioGit = repository.findByusergithub(nomeUsuario);
		Assert.assertNotNull(usuarioGit);
		Assert.assertEquals(nomeUsuario, usuarioGit.get().getUsergithub());
	}

	
	
}
