package com.boasaude.prestador.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Prestador")
public class Prestador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 2, max = 255)
	private String nome;

	@Size(min = 5)
	@Column(unique = true)
	private String usuario;

	@Size(min = 5)
	private String email;

	@Size(min = 8)
	private String senha;

	@Size(min = 2, max = 32)
	@Column(unique = true)
	private String cpf;

	@Size(min = 9, max = 9)
	private String matricula;
	
	@Size(min = 9, max = 32)
	private String especialidade;

	
	public enum especialidade  {
	    MEDICO, ENFERMEIRO, FISIOTERAPEUTA, DENTISTA;
	}

	private boolean admin;
	
	@Deprecated
	public Prestador() {
	}

	public Prestador(String nome) {
		this.nome = nome;
	}

	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}