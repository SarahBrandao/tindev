package com.soft.tindev.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.soft.tindev.modelo.Usuario;

public class UsuarioDto {

	private Long id;
	private String nome;
	private String usergithub;
	
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.usergithub = usuario.getUsergithub();
	}
	

	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}


	public String getUsergithub() {
		return usergithub;
	}


	public static List<UsuarioDto> converter(List<Usuario> usuarios) {		
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}
	
	
	
	
}
