package com.sidney.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sidney.helpdesk.services.ChamadoService;
import com.sidney.helpdesk.domain.Chamado;
import com.sidney.helpdesk.domain.Cliente;
import com.sidney.helpdesk.domain.dtos.ChamadoDTO;

// camada de recurso que se comunica com a camada de serviço
@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

	@Autowired	
	private ChamadoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
		Chamado obj = service.findById(id);
		
		System.out.println("findById::  ");
		System.out.println("obj.getId()" + obj.getId());
		System.out.println("obj.getTitulo" + obj.getObservacoes());
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll(){
		List<Chamado> list = service.findAll();
		List<ChamadoDTO> listDTO = list.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);		
	}
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> create (@Valid @RequestBody ChamadoDTO objDTO){
		Chamado obj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();				
	}
	
}
