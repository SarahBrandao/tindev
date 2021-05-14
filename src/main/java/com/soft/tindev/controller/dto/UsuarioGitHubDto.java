package com.soft.tindev.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.soft.tindev.modelo.Usuario;

public class UsuarioGitHubDto {
	private Long id;
	private String name;
	private String usergithub;
	private String biogithub;
	private String avatar_url;
	private String company;
	
	
	
	public UsuarioGitHubDto(Usuario usuario, GitHubDto githubdto) {
		this.id = usuario.getId();
		this.name = githubdto.getName();
		this.usergithub = usuario.getUsergithub();
		this.biogithub = githubdto.getBio();
		this.avatar_url = githubdto.getAvatar_url();
		this.company = githubdto.getCompany();
	}
	
	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getUsergithub() {
		return usergithub;
	}


	public String getBiogithub() {
		return biogithub;
	}


	public String getAvatar_url() {
		return avatar_url;
	}


	public String getCompany() {
		return company;
	}

	

	
}
