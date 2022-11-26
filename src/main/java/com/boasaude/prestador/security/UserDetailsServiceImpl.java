package com.boasaude.prestador.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boasaude.prestador.model.Prestador;
import com.boasaude.prestador.repository.PrestadorRepository;

@Service
public class UserDetailsServiceImpl {
	@Autowired
	private PrestadorRepository prestadorRepository;
	
	public UserDetailsImpl loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Prestador> user = prestadorRepository.findByUsuario(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
		
		return user.map(UserDetailsImpl::new).get();
	}

}