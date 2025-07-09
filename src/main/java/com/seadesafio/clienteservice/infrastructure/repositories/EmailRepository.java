package com.seadesafio.clienteservice.infrastructure.repositories;

import com.seadesafio.clienteservice.domain.entities.EmailContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailContato, Long> {
}
