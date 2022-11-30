package com.boasaude.prestador.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boasaude.prestador.model.Prestador;
import com.boasaude.prestador.model.PrestadorLogin;
import com.boasaude.prestador.repository.PrestadorRepository;
import com.boasaude.prestador.service.PrestadorService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/prestador")
public class PrestadorController {
	
	@Autowired
	private PrestadorRepository repository;
	
	@Autowired
	private PrestadorService prestadorService;
	
	@GetMapping
	public ResponseEntity<List<Prestador>> getAllPrestador()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<Prestador> findByIdPrestador(@PathVariable long id)
	{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Optional<Prestador>> getByNome(@PathVariable String nome)
	{
		return ResponseEntity.ok(repository.findByNome(nome));
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Optional<Prestador>> getByCpf(@PathVariable String cpf)
	{
		return ResponseEntity.ok(repository.findByCpf(cpf));
	}
	
	@PostMapping
	public ResponseEntity<Prestador> post(@RequestBody Prestador prestador)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(prestador));
	}
	
	@PutMapping
	public ResponseEntity<Prestador> put(@RequestBody Prestador prestador)
	{
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(prestador));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id)
	{
		repository.deleteById(id);
	}
	
	@PostMapping("/logar")
	public ResponseEntity<PrestadorLogin> Autentication(@RequestBody Optional<PrestadorLogin> user) {
		return prestadorService.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Prestador> Post(@RequestBody Prestador usuario) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(prestadorService.cadastrarUsuario(usuario));
	}
}