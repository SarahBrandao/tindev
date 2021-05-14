package com.soft.tindev.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.soft.tindev.controller.dto.GitHubDto;
import com.soft.tindev.controller.dto.UsuarioDto;
import com.soft.tindev.controller.dto.UsuarioGitHubDto;
import com.soft.tindev.controller.form.AtualizacaoUsuarioForm;
import com.soft.tindev.controller.form.UsuarioForm;
import com.soft.tindev.modelo.Match;
import com.soft.tindev.modelo.Usuario;
import com.soft.tindev.repository.MatchRepository;
import com.soft.tindev.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MatchRepository matchRepository;

	// listando usuários, apenas lista id, nome e user do git e implementando cache
	// no cadastrar
	@GetMapping
	@Cacheable(value = "listaDeUsuarios")
	public List<UsuarioDto> lista() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return UsuarioDto.converter(usuarios);
	}

	// Listando usuarios do guthub com seus atributos vindos de lá, APENAS 1 POR VEZ
	@GetMapping("/{usergithub}")
	public UsuarioGitHubDto listarUsuarioUnico(@PathVariable("usergithub") String usergithub) {
		// comunicação c api externa do github
		RestTemplate restTemplate = new RestTemplate();
		GitHubDto usuariogithub = restTemplate.getForObject("https://api.github.com/users/" + usergithub,
				GitHubDto.class);

		Optional<Usuario> teste = usuarioRepository.findByusergithub(usergithub);
		UsuarioGitHubDto usuariodto = new UsuarioGitHubDto(teste.get(), usuariogithub);

		return usuariodto;
	}

	// Cadastrando novos usuarios e invalidando o cache no cadastro de novos
	// usuarios
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeUsuarios", allEntries = true)
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
		Usuario usuario = form.converter();
		usuarioRepository.save(usuario);

		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}

	// Atualizando nome e/ou user do git e invalidando o cache
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeUsuarios", allEntries = true)
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid AtualizacaoUsuarioForm form) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			Usuario usuario = form.atualizar(id, usuarioRepository);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}

		return ResponseEntity.notFound().build();
	}

	// Excluindo usuarios e invalidando o cache
	@DeleteMapping("/{id}")
	@Transactional
	//@CacheEvict(value = "listaDeUsuarios", allEntries = true)
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	// Metodo para dar like
	@PostMapping("/like/{idLiking}/{idLiked}")
	@Transactional
	public ResponseEntity<?> LikingAndLiked(@PathVariable Long idLiking, @PathVariable Long idLiked) {
		Optional<Usuario> liking = usuarioRepository.findById(idLiking);
		Optional<Usuario> liked = usuarioRepository.findById(idLiked);

		Match match = new Match(liking.get(), liked.get());

		matchRepository.save(match);
		System.out.println(match.getId());
		return ResponseEntity.ok().build();
	}

	// metodo que mostra em quem voce deu like
	@GetMapping("/{id}/likings")
	public ResponseEntity<List<UsuarioDto>> listlikings(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		List<UsuarioDto> usuarioQueDeuOsLikes = UsuarioDto.converter(usuario.get().getLikings());

		return ResponseEntity.ok().body(usuarioQueDeuOsLikes);
	}

	// metodo que mostra quem te deu like
	@GetMapping("/{id}/likes")
	public ResponseEntity<List<UsuarioDto>> listLikes(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		List<UsuarioDto> usuariosQueMeDeramLike = UsuarioDto.converter(usuario.get().getLikeds());

		return ResponseEntity.ok().body(usuariosQueMeDeramLike);
	}
	
	
	//metodo que mostra os matchs
	@GetMapping("/{id}/matchs")
	public ResponseEntity<List<UsuarioDto>> listMatchs(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		List<UsuarioDto> matchs = UsuarioDto.converter(usuario.get().getMatchs());

		return ResponseEntity.ok().body(matchs);
	}

}
