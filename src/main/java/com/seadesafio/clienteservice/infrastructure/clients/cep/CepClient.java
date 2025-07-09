package com.seadesafio.clienteservice.infrastructure.clients.cep;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CepClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "https://brasilapi.com.br/api/cep/v1/";

    public CepResponse buscar(String cep) {
        String endpoint = URL + cep;
        return restTemplate.getForObject(endpoint, CepResponse.class);
    }
}
