package com.seadesafio.clienteservice.application.usecases;

import com.seadesafio.clienteservice.domain.entities.Usuario;
import com.seadesafio.clienteservice.infrastructure.security.JwtService;
import com.seadesafio.clienteservice.web.dtos.AuthRequest;
import com.seadesafio.clienteservice.web.dtos.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse autenticar(AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getSenha())
        );

        Usuario usuario = (Usuario) auth.getPrincipal();
        String token = jwtService.gerarToken(usuario.getUsername());
        return new AuthResponse(token);
    }
}
