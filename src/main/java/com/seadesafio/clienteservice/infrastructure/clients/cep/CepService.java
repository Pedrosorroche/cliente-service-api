package com.seadesafio.clienteservice.infrastructure.clients.cep;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class CepService {

    private final RestTemplate restTemplate;

    public CepResponse consultarCep(String cep) {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://brasilapi.com.br/api/cep/v1/" + cep)
                .toUriString();

        return restTemplate.getForObject(url, CepResponse.class);
    }
}
