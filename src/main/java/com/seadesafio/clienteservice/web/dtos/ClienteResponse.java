package com.seadesafio.clienteservice.web.dtos;

import com.seadesafio.clienteservice.domain.entities.Cliente;
import com.seadesafio.clienteservice.utils.MascaraUtils;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ClienteResponse {
    private Long id;
    private String nome;
    private String cpf;
    private EnderecoDTO endereco;
    private List<TelefoneDTO> telefones;
    private List<EmailDTO> emails;

    public static ClienteResponse fromEntity(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(MascaraUtils.formatarCpf(cliente.getCpf()))
                .endereco(EnderecoDTO.fromEntity(cliente.getEndereco()))
                .telefones(cliente.getTelefones().stream()
                        .map(TelefoneDTO::fromEntity)
                        .collect(Collectors.toList()))
                .emails(cliente.getEmails().stream()
                        .map(EmailDTO::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
