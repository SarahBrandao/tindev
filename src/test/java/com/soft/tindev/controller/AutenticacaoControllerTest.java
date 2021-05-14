package com.soft.tindev.controller;


import java.net.URI;

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


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AutenticacaoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	//Neste teste, verifica-se se o controller está realmente devolvendo Bad Request quando entram na aplicação c user e senha errados
	@Test
	public void testBadRequest() throws Exception {
		URI uri = new URI("/auth");
		String json = "{\"usergithub\":\"testeinvalido\", \"senha\":\"654321\"}";
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(400));
	}

}
