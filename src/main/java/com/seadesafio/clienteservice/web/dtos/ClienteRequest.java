package com.seadesafio.clienteservice.web.dtos;

import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    @Pattern(regexp = "^[A-Za-zÀ-ÿ0-9 ]+$", message = "O nome deve conter apenas letras, números e espaços")
    private String nome;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    private EnderecoDTO endereco;

    @Size(min = 1)
    private List<TelefoneDTO> telefones;

    @Size(min = 1)
    private List<EmailDTO> emails;
}
