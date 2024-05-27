package com.sidney.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.sidney.helpdesk.domain.Cliente;
import com.sidney.helpdesk.domain.Pessoa;
import com.sidney.helpdesk.domain.dtos.ClienteDTO;
import com.sidney.helpdesk.repositories.ClienteRepository;
import com.sidney.helpdesk.repositories.PessoaRepository;
import com.sidney.helpdesk.services.exceptions.ObjectnotFoundException;

import org.springframework.dao.DataIntegrityViolationException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;	
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto nao encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {		 
		return repository.findAll();
	}
	 
	public Cliente create(ClienteDTO objDTO) {	
		objDTO.setId(null);
		validaPorCpfEmail(objDTO);
		Cliente newObj = new Cliente (objDTO);
		return repository.save(newObj); // chamada assincrona 
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		
		validaPorCpfEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}		

	public void delete(Integer id) {
		Cliente obj = findById(id);
		
		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviços e não pode ser deletado!");
		}
		
		repository.deleteById(id);				
	}

	private void validaPorCpfEmail(ClienteDTO objDTO) {				
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());	
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
				throw new DataIntegrityViolationException("CPF ja cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
				throw new DataIntegrityViolationException("E-mail ja cadastrado no sistema!");
		} 
	}
	

}

