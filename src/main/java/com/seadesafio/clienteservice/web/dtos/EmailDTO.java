package com.seadesafio.clienteservice.web.dtos;

import com.seadesafio.clienteservice.domain.entities.EmailContato;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDTO {
    private String endereco;

    public static EmailDTO fromEntity(EmailContato emailContato) {
        return EmailDTO.builder()
                .endereco(emailContato.getEndereco())
                .build();
    }
}
