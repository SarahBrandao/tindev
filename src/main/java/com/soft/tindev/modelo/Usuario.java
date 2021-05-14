package com.soft.tindev.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String usergithub;
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();
	@OneToMany(mappedBy = "liking")
	private List<Match> likings = new ArrayList<>();
	@OneToMany(mappedBy = "liked")
	private List<Match> likeds = new ArrayList<>();

	public Usuario() {

	}

	public Usuario(String nome, String usergithub, String senha) {
		this.nome = nome;
		this.usergithub = usergithub;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsergithub() {
		return usergithub;
	}

	public void setUsergithub(String usergithub) {
		this.usergithub = usergithub;
	}

	public List<Usuario> getLikeds() {
		List<Usuario> usuarios = new ArrayList<>();
		likeds.forEach(match -> {
			usuarios.add(match.getLiking());
		});
		return usuarios;
	}

	public List<Usuario> getLikings() {
		List<Usuario> usuarios = new ArrayList<>();
		likings.forEach(match -> {
			usuarios.add(match.getLiked());
		});
		return usuarios;
	}

	
	
	public List<Usuario> getMatchs() {
		List<Usuario> usuarios = new ArrayList<>();
		likings.forEach(liking -> {
			likeds.forEach(liked -> {
				if (liking.getLiked().getId() == liked.getLiking().getId()) {
					usuarios.add(liking.getLiked());
				}
			});
		});
		return usuarios;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		// No login, não entraremos com o e-mail, mas sim com o user do github
		return this.usergithub;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
