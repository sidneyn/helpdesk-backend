package com.sidney.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sidney.helpdesk.domain.dtos.TecnicoDTO;
import com.sidney.helpdesk.domain.enums.Perfil;

@Entity
public class Tecnico extends Pessoa {
	private static final long serialVersionUID = 1L;

	// relacionamento um tecnico para muitos chamados
	// sendo mapeado pela classe tecnico na classe chamado
	
	@JsonIgnore	 // protege a requisicao feita pelo get da serialização que faz um loop dos dados quando requisitado na Api-Rest Postman
	@OneToMany(mappedBy = "tecnico") 	// um Tecnico para muitos chamados
	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();		
		addPerfil(Perfil.CLIENTE);
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}

	public Tecnico(TecnicoDTO obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}
