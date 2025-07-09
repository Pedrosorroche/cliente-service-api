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
public class AtualizarClienteUseCase {

    private final ClienteRepository clienteRepository;
    private final TelefoneRepository telefoneRepository;
    private final EmailRepository emailRepository;
    private final EnderecoRepository enderecoRepository;
    private final CepClient cepClient;

    @Transactional
    public ClienteResponse executar(Long id, ClienteRequest dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        CepResponse cepResponse = cepClient.buscar(dto.getEndereco().getCep());

        Endereco endereco = cliente.getEndereco();
        endereco.setCep(dto.getEndereco().getCep());
        endereco.setNumero(dto.getEndereco().getNumero());
        endereco.setComplemento(dto.getEndereco().getComplemento());
        endereco.setRua(cepResponse.getStreet());
        endereco.setBairro(cepResponse.getNeighborhood());
        endereco.setCidade(cepResponse.getCity());
        endereco.setEstado(cepResponse.getState());

        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setEndereco(endereco);

        cliente.getTelefones().clear();
        List<Telefone> telefones = dto.getTelefones().stream()
                .map(t -> Telefone.builder()
                        .tipo(t.getTipo())
                        .numero(t.getNumero())
                        .cliente(cliente)
                        .build())
                .collect(Collectors.toList());
        cliente.getTelefones().addAll(telefones);

        cliente.getEmails().clear();
        List<EmailContato> emails = dto.getEmails().stream()
                .map(e -> EmailContato.builder()
                        .endereco(e.getEndereco())
                        .cliente(cliente)
                        .build())
                .collect(Collectors.toList());
        cliente.getEmails().addAll(emails);

        Cliente atualizado = clienteRepository.save(cliente);

        return ClienteResponse.builder()
                .id(atualizado.getId())
                .nome(atualizado.getNome())
                .cpf(atualizado.getCpf())
                .endereco(dto.getEndereco())
                .telefones(dto.getTelefones())
                .emails(dto.getEmails())
                .build();
    }
}
