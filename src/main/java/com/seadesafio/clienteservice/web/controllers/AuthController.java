package com.seadesafio.clienteservice.web.controllers;

import com.seadesafio.clienteservice.application.usecases.AuthUseCase;
import com.seadesafio.clienteservice.web.dtos.AuthRequest;
import com.seadesafio.clienteservice.web.dtos.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        AuthResponse response = authUseCase.autenticar(request);
        return ResponseEntity.ok(response);
    }
}
