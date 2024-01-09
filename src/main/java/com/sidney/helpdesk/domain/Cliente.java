package com.sidney.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.sidney.helpdesk.domain.enums.Perfil;

@Entity
public class Cliente extends Pessoa {
	
	// relacionamento um tecnico para muitos chamados
	// sendo mapeado pela classe tecnico na classe chamado			
	@OneToMany(mappedBy = "cliente") 
	private List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();		
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);		
		addPerfil(Perfil.CLIENTE);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

	
}
