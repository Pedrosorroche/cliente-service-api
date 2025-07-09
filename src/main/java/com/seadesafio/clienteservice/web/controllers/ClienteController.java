package com.seadesafio.clienteservice.web.controllers;

import com.seadesafio.clienteservice.application.usecases.*;
import com.seadesafio.clienteservice.web.dtos.ClienteRequest;
import com.seadesafio.clienteservice.web.dtos.ClienteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final CriarClienteUseCase criarClienteUseCase;
    private final ListarClientesUseCase listarClientesUseCase;
    private final BuscarClientePorIdUseCase buscarClientePorIdUseCase;
    private final AtualizarClienteUseCase atualizarClienteUseCase;
    private final ExcluirClienteUseCase excluirClienteUseCase;

    public ClienteController(CriarClienteUseCase criarClienteUseCase, ListarClientesUseCase listarClientesUseCase, BuscarClientePorIdUseCase buscarClientePorIdUseCase, AtualizarClienteUseCase atualizarClienteUseCase, ExcluirClienteUseCase excluirClienteUseCase) {
        this.criarClienteUseCase = criarClienteUseCase;
        this.listarClientesUseCase = listarClientesUseCase;
        this.buscarClientePorIdUseCase = buscarClientePorIdUseCase;
        this.atualizarClienteUseCase = atualizarClienteUseCase;
        this.excluirClienteUseCase = excluirClienteUseCase;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> criarCliente(@RequestBody @Validated ClienteRequest request) {
        ClienteResponse response = criarClienteUseCase.executar(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarClientes() {
        List<ClienteResponse> clientes = listarClientesUseCase.executar();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable Long id) {
        ClienteResponse cliente = buscarClientePorIdUseCase.executar(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable Long id, @RequestBody @Validated ClienteRequest dto) {
        ClienteResponse atualizado = atualizarClienteUseCase.executar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirClienteUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
