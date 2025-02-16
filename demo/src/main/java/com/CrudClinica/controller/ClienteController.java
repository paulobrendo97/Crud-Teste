package com.CrudClinica.controller;

import com.CrudClinica.model.Cliente;
import com.CrudClinica.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    // Cadastro de cliente
    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestParam String cpf, @RequestParam String nome, @RequestParam String email) {
        Cliente cliente = service.cadastrarCliente(cpf, nome, email);
        return ResponseEntity.ok(cliente);
    }

    // Atualização de e-mail
    @PutMapping("/{cpf}/email")
    public ResponseEntity<Cliente> atualizarEmail(@PathVariable String cpf, @RequestParam String novoEmail) {
        Cliente cliente = service.atualizarEmail(cpf, novoEmail);
        return ResponseEntity.ok(cliente);
    }

    // Exclusão de cliente por CPF
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> excluirCliente(@PathVariable String cpf) {
        service.excluirCliente(cpf);
        return ResponseEntity.noContent().build();
    }

    // Consulta por CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable String cpf) {
        return service.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Consulta geral (todos os clientes)
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
