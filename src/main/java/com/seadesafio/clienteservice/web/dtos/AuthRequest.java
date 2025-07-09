package com.seadesafio.clienteservice.web.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String senha;
}
