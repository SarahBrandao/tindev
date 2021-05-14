package com.soft.tindev.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.soft.tindev.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
@Profile(value = {"dev", "test"})
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//Metodo que serve para configurar configs de autenticação - login
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	//Metodo de configs de autorização - quem pode acessar cada URL 
	//VERIFICAR O SEGUNDO NEGOCIO
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/usuarios").permitAll()
		.antMatchers(HttpMethod.POST, "/usuarios/**").permitAll()
		.antMatchers(HttpMethod.POST, "/auth/**").permitAll()
		.antMatchers("/h2-console").permitAll()
		.antMatchers("/h2-console/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().headers().frameOptions().sameOrigin()
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
}
