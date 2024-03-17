package com.sidney.helpdesk;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sidney.helpdesk.domain.Cliente;
import com.sidney.helpdesk.domain.Chamado;
import com.sidney.helpdesk.domain.Tecnico;
import com.sidney.helpdesk.domain.enums.Perfil;
import com.sidney.helpdesk.domain.enums.Prioridade;
import com.sidney.helpdesk.domain.enums.Status;
import com.sidney.helpdesk.repositories.ChamadoRepository;
import com.sidney.helpdesk.repositories.ClienteRepository;
import com.sidney.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired // responsavel por criar gerenciar e destruir a instancia
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Tecnico tec1 = new Tecnico(null, "Sidney Nogueira", "485.180.130-03","sidneyn@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
	
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "739.006.970-86", "torvalds@mail.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01","Primeiro Chamado",tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}

}
