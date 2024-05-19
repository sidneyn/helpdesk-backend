package com.sidney.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sidney.helpdesk.domain.enums.Perfil;

//responsavel por criar a tabela no banco de dados  serializable 

@Entity   // informando que a classe pessoa é uma entidade é o banco deve criar a tabela
public abstract class Pessoa implements Serializable{	
	private static final long serialVersionUID = 1L;

	// o tipo protected permite que as classes de herança possa ver os atributos da classe pai herança
	@Id // chave primaria 
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //geracao do id será do banco de dados
	protected Integer id;
	protected String nome;
	
	@CPF
	@Column(unique = true) // javax.persistem nao tera cpf dubplicado
	protected String cpf;
	
	@Column(unique = true) // nao email dubplicado
	protected String email;
	protected String senha;
	
	@ElementCollection(fetch = FetchType.EAGER) // colecao de elementos tipo inter. vai carrega a lista de perfil na lista de usuario na lista de perfil 
	@CollectionTable(name = "PERFIS")
	protected Set<Integer> perfis = new HashSet<>(); // HashSet evita a exceçao de ponteiro nulo
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();	
		// toda pessoa criada no sistema que nao tenha nenhum parametro ele terar o perfil definido como cliente como padrão
	public Pessoa() {
		super();
		addPerfil(Perfil.CLIENTE); 
	}
	
	public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}

	// metodos acessores e manipuladores
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}
	 
	
	
}
