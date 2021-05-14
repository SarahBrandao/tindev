package com.soft.tindev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.tindev.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByusergithub(String usergithub);

	//Nesta classe é puxado métodos previamente existentes na JPA
}
