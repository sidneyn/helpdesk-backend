package com.sidney.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sidney.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
