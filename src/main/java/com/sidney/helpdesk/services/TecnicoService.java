package com.sidney.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.sidney.helpdesk.domain.Pessoa;
import com.sidney.helpdesk.domain.Tecnico;
import com.sidney.helpdesk.domain.dtos.TecnicoDTO;
import com.sidney.helpdesk.repositories.PessoaRepository;
import com.sidney.helpdesk.repositories.TecnicoRepository;
import com.sidney.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;	
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto nao encontrado! Id: " + id));
	}

	public List<Tecnico> findAll() {		 
		return repository.findAll();
	}
	 
	public Tecnico create(TecnicoDTO objDTO) {	
		objDTO.setId(null);
		validaPorCpfEmail(objDTO);
		Tecnico newObj = new Tecnico (objDTO);
		return repository.save(newObj); // chamada assincrona 
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
		validaPorCpfEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		return repository.save(oldObj);
	}		

	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Tecnico possui ordens de serviços e não pode ser deletado!");
		}
		
		repository.deleteById(id);				
	}

/**
 * Metodo valida o cpf antes de ser inserido e nao causar uma violação de dados no banco  
 * @param objDTO
 * @return string msg levantando a exceção antes 
 */
	private void validaPorCpfEmail(TecnicoDTO objDTO) {
				
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

