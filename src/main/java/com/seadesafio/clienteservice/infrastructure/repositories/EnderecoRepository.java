package com.seadesafio.clienteservice.infrastructure.repositories;

import com.seadesafio.clienteservice.domain.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
