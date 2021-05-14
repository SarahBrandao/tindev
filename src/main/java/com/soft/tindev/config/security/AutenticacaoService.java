package com.soft.tindev.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.soft.tindev.modelo.Usuario;
import com.soft.tindev.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = repository.findByusergithub(username);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		
		throw new UsernameNotFoundException("Dados inválidos!");
	}

}
