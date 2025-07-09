package com.seadesafio.clienteservice.infrastructure.repositories;

import com.seadesafio.clienteservice.domain.entities.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
