package com.seadesafio.clienteservice.web.dtos;

import com.seadesafio.clienteservice.domain.entities.Endereco;
import com.seadesafio.clienteservice.utils.MascaraUtils;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoDTO {
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String numero;
    private String complemento;

    public static EnderecoDTO fromEntity(Endereco endereco) {
        return EnderecoDTO.builder()
                .cep(MascaraUtils.formatarCep(endereco.getCep()))
                .rua(endereco.getRua())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .build();
    }
}
