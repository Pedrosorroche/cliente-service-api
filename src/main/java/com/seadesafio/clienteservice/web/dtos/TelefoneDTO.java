package com.seadesafio.clienteservice.web.dtos;

import com.seadesafio.clienteservice.domain.entities.Telefone;
import com.seadesafio.clienteservice.utils.MascaraUtils;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TelefoneDTO {
    private String tipo;
    private String numero;

    public static TelefoneDTO fromEntity(Telefone telefone) {
        return TelefoneDTO.builder()
                .tipo(telefone.getTipo())
                .numero(MascaraUtils.formatarTelefone(telefone.getNumero()))
                .build();
    }
}
