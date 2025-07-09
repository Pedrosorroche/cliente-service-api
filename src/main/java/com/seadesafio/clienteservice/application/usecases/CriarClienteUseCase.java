package com.seadesafio.clienteservice.application.usecases;

import com.seadesafio.clienteservice.domain.entities.*;
import com.seadesafio.clienteservice.infrastructure.clients.cep.CepClient;
import com.seadesafio.clienteservice.infrastructure.clients.cep.CepResponse;
import com.seadesafio.clienteservice.infrastructure.repositories.*;
import com.seadesafio.clienteservice.web.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CriarClienteUseCase {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;
    private final EmailRepository emailRepository;
    private final CepClient cepClient;

    @Transactional
    public ClienteResponse executar(ClienteRequest dto) {
        if (clienteRepository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF já cadastrado.");
        }

        CepResponse cepResponse = cepClient.buscar(dto.getEndereco().getCep());

        Endereco endereco = Endereco.builder()
                .cep(dto.getEndereco().getCep())
                .numero(dto.getEndereco().getNumero())
                .complemento(dto.getEndereco().getComplemento())
                .rua(cepResponse.getStreet())
                .bairro(cepResponse.getNeighborhood())
                .cidade(cepResponse.getCity())
                .estado(cepResponse.getState())
                .build();

        Cliente cliente = Cliente.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .endereco(endereco)
                .build();

        Cliente clienteSalvo = clienteRepository.save(cliente);

        List<Telefone> telefones = dto.getTelefones().stream()
                .map(t -> Telefone.builder()
                        .tipo(t.getTipo())
                        .numero(t.getNumero())
                        .cliente(clienteSalvo)
                        .build())
                .collect(Collectors.toList());
        telefoneRepository.saveAll(telefones);

        List<EmailContato> emails = dto.getEmails().stream()
                .map(e -> EmailContato.builder()
                        .endereco(e.getEndereco())
                        .cliente(clienteSalvo)
                        .build())
                .collect(Collectors.toList());
        emailRepository.saveAll(emails);

        clienteSalvo.setTelefones(telefones);
        clienteSalvo.setEmails(emails);

        return ClienteResponse.builder()
                .id(clienteSalvo.getId())
                .nome(clienteSalvo.getNome())
                .cpf(clienteSalvo.getCpf())
                .endereco(dto.getEndereco())
                .telefones(dto.getTelefones())
                .emails(dto.getEmails())
                .build();
    }
}
