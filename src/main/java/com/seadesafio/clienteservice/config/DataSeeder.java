package com.seadesafio.clienteservice.config;

import com.seadesafio.clienteservice.domain.entities.Usuario;
import com.seadesafio.clienteservice.domain.enums.Role;
import com.seadesafio.clienteservice.infrastructure.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!usuarioRepository.existsByUsername("admin")) {
            Usuario admin = Usuario.builder()
                    .username("admin")
                    .senha(passwordEncoder.encode("123qwe!@#"))
                    .roles(Collections.singletonList(Role.ROLE_ADMIN))
                    .build();
            usuarioRepository.save(admin);
        }

        if (!usuarioRepository.existsByUsername("padrão")) {
            Usuario user = Usuario.builder()
                    .username("padrão")
                    .senha(passwordEncoder.encode("123qwe123"))
                    .roles(Collections.singletonList(Role.ROLE_USER))
                    .build();
            usuarioRepository.save(user);
        }
    }
}