package com.seadesafio.clienteservice.application.usecases;

import com.seadesafio.clienteservice.domain.entities.Cliente;
import com.seadesafio.clienteservice.infrastructure.repositories.ClienteRepository;
import com.seadesafio.clienteservice.web.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuscarClientePorIdUseCase {

    private final ClienteRepository clienteRepository;

    public ClienteResponse executar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .endereco(EnderecoDTO.builder()
                        .cep(cliente.getEndereco().getCep())
                        .numero(cliente.getEndereco().getNumero())
                        .complemento(cliente.getEndereco().getComplemento())
                        .rua(cliente.getEndereco().getRua())
                        .bairro(cliente.getEndereco().getBairro())
                        .cidade(cliente.getEndereco().getCidade())
                        .estado(cliente.getEndereco().getEstado())
                        .build())
                .telefones(cliente.getTelefones().stream()
                        .map(t -> TelefoneDTO.builder()
                                .tipo(t.getTipo())
                                .numero(t.getNumero())
                                .build())
                        .collect(Collectors.toList()))
                .emails(cliente.getEmails().stream()
                        .map(e -> EmailDTO.builder()
                                .endereco(e.getEndereco())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
