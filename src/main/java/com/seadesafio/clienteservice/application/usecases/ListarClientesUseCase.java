package com.seadesafio.clienteservice.application.usecases;

import com.seadesafio.clienteservice.domain.entities.Cliente;
import com.seadesafio.clienteservice.infrastructure.repositories.ClienteRepository;
import com.seadesafio.clienteservice.web.dtos.ClienteResponse;
import com.seadesafio.clienteservice.web.dtos.EmailDTO;
import com.seadesafio.clienteservice.web.dtos.EnderecoDTO;
import com.seadesafio.clienteservice.web.dtos.TelefoneDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarClientesUseCase {

    private final ClienteRepository clienteRepository;

    public List<ClienteResponse> executar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(ClienteResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
