package com.soft.tindev.controller;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.soft.tindev.controller.dto.UsuarioDto;
import com.soft.tindev.controller.form.UsuarioForm;
import com.soft.tindev.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuarioControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UsuarioController usuarioController;
	
	@Autowired
	private UsuarioForm usuarioForm;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	public void testValidandoCadastroNome() throws Exception {
		URI uri = new URI("/usuarios");
		String nome = "{\"Sa\"}";
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(nome).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	@Test
	public void testSavos() {
		List <UsuarioDto> lista = usuarioController.lista();
		
		assertEquals("Sarah", lista.get(0).getNome());
		assertEquals("Carol", lista.get(1).getNome());
	}

}
