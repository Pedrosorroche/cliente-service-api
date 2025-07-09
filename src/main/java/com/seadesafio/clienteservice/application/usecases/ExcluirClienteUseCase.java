package com.seadesafio.clienteservice.application.usecases;

import com.seadesafio.clienteservice.infrastructure.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExcluirClienteUseCase {

    private final ClienteRepository clienteRepository;

    @Transactional
    public void executar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado com ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
