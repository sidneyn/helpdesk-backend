package com.sidney.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.sidney.helpdesk.domain.enums.Perfil;

@Entity
public class Tecnico extends Pessoa {
	private static final long serialVersionUID = 1L;

	// relacionamento um tecnico para muitos chamados
	// sendo mapeado pela classe tecnico na classe chamado
		
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
	
	

}
