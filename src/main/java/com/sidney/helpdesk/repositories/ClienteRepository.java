package com.sidney.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sidney.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
