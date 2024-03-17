package com.sidney.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sidney.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
