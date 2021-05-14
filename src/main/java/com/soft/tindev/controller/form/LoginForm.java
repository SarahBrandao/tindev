package com.soft.tindev.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
	
	private String usergithub;
	private String senha;
	
	
	
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public String getUsergithub() {
		return usergithub;
	}

	public void setUsergithub(String usergithub) {
		this.usergithub = usergithub;
	}

	public UsernamePasswordAuthenticationToken converter() {		
		return new UsernamePasswordAuthenticationToken(usergithub, senha);
	}
	
	

}
