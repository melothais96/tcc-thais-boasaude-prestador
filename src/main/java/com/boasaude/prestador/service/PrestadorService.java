package com.boasaude.prestador.service;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.boasaude.prestador.model.Prestador;
import com.boasaude.prestador.model.PrestadorLogin;
import com.boasaude.prestador.repository.PrestadorRepository;

@Service
public class PrestadorService {
	@Autowired
	private PrestadorRepository repository;

	private static final Logger LOGGER = Logger.getLogger(PrestadorService.class.getName());

	public PrestadorService(PrestadorRepository repository) {
		this.repository = repository;
	}

	public Prestador cadastrarUsuario(Prestador prestador) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(prestador.getSenha());
		prestador.setSenha(senhaEncoder);

		return repository.save(prestador);
	}

	public Optional<PrestadorLogin> logar(Optional<PrestadorLogin> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Prestador> usuario = repository.findByUsuario(user.get().getUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				user.get().setId(usuario.get().getId());
				user.get().setAdmin(usuario.get().isAdmin());

				return user;

			}
		}
		return null;
	}

	public void run(ApplicationArguments args) throws Exception {
		Prestador prestador = new Prestador("Gaspar Barancelli Junior");

		LOGGER.log(Level.INFO, "Persist");
		repository.save(prestador);
		LOGGER.log(Level.INFO, prestador.toString());

		LOGGER.log(Level.INFO, "Find");
		repository.findById(prestador.getId()).ifPresent(it -> {
			LOGGER.log(Level.INFO, prestador.toString());
		});

		Prestador prestador2 = new Prestador("Rodrigo Barancelli");

		LOGGER.log(Level.INFO, "Persist");
		repository.save(prestador2);
		LOGGER.log(Level.INFO, prestador2.toString());

		prestador2.setNome("Rodrigo Dalla Valle Barancelli");
		LOGGER.log(Level.INFO, "Update");
		repository.save(prestador2);
		LOGGER.log(Level.INFO, prestador2.toString());

		LOGGER.log(Level.INFO, "FindAll");
		repository.findAll().forEach(it -> LOGGER.log(Level.INFO, it.toString()));

		LOGGER.log(Level.INFO, "Delete");
		repository.delete(prestador2);
		LOGGER.log(Level.INFO, prestador2.toString());
	}
}