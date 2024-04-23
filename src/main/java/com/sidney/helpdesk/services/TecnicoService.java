package com.sidney.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidney.helpdesk.domain.Tecnico;
import com.sidney.helpdesk.domain.dtos.TecnicoDTO;
import com.sidney.helpdesk.repositories.TecnicoRepository;
import com.sidney.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto nao encontrado! Id: " + id));
	}

	public List<Tecnico> findAll() {		 
		return repository.findAll();
	}
	
	public Tecnico create(TecnicoDTO objDTO) {	
		objDTO.setId(null);
		Tecnico newObj = new Tecnico (objDTO);
		return repository.save(newObj); // chamada assincrona 
	}

}
