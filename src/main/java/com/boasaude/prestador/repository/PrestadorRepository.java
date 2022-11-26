package com.boasaude.prestador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boasaude.prestador.model.Prestador;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Long>{
	public Optional<Prestador> findByUsuario(String usuario);
	public Optional<Prestador> findByNome(String nome);
	public Optional<Prestador> findByCpf(String cpf);
}