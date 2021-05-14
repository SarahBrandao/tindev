package com.soft.tindev.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.soft.tindev.modelo.Usuario;

@Component
public class UsuarioForm {
	
	@NotNull @NotEmpty @Length(min = 3)
	private String nome;
	
	@NotNull @NotEmpty @Length(min = 5)
	private String usergithub;
	
	@NotNull @NotEmpty @Length(min = 4)
	private String senha;
	
	
	public String getNome() {
		return nome;
	}
	
	public String getUsergithub() {
		return usergithub;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setUsergithub(String usergithub) {
		this.usergithub = usergithub;
	}

	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Usuario converter() {		
		String senhaCriptografada = new BCryptPasswordEncoder().encode(senha);
		return new Usuario(nome, usergithub, senhaCriptografada);
	}

	public static void main(String[] args) {
		String senhaCriptografada = new BCryptPasswordEncoder().encode("123456");
		System.out.println(senhaCriptografada);
	}
		
}
