package com.sidney.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sidney.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
