package com.soft.tindev.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.soft.tindev.modelo.Usuario;
import com.soft.tindev.repository.UsuarioRepository;

public class AtualizacaoUsuarioForm {
	
	@NotNull @NotEmpty @Length(min = 3)
	private String nome;
	
	@NotNull @NotEmpty @Length(min = 5)
	private String usergithub;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsergithub() {
		return usergithub;
	}

	public void setUsergithub(String usergithub) {
		this.usergithub = usergithub;
	}

	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(id);
		usuario.setNome(this.nome);
		usuario.setUsergithub(this.usergithub);
		
		return usuario;
	}
}
