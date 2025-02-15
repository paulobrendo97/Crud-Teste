package com.CrudClinica.controller;

import com.CrudClinica.model.Cliente; //Representa a entidade Cliente.
import com.CrudClinica.service.ClienteService;
import org.springframework.http.ResponseEntity;// Usado para personalizar as respostas HTTP.
import org.springframework.web.bind.annotation.*;

import java.util.List; //Estruturas de dados utilizadas na busca de clientes.
import java.util.Optional; //Estruturas de dados utilizadas na busca de clientes.

@RestController//: Indica que esta classe é um controlador REST.
@RequestMapping("/clientes")//Define a URL base para os endpoints da classe. Define que todas as requisições dessa classe começam com /clientes.
public class ClienteController {

    private final ClienteService service;//Declara o serviço que será usado para executar as regras de negócio dos clientes.

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestParam String cpf, @RequestParam String nome, @RequestParam String email, @RequestParam boolean especial) {
        Cliente cliente = service.cadastrarCliente(cpf, nome, email, especial);
        return ResponseEntity.ok(cliente);
        // Define um endpoint para cadastrar um novo cliente usando POST /clientes.
        //Os dados do cliente (cpf, nome, email, especial) são passados via parâmetros da URL (@RequestParam).
    }

    @PutMapping("/{cpf}/email")
    public ResponseEntity<Cliente> atualizarEmail(@PathVariable String cpf, @RequestParam String novoEmail) {
        Cliente cliente = service.atualizarEmail(cpf, novoEmail);
        return ResponseEntity.ok(cliente);
        //Chama o serviço para atualizar o e-mail e retorna o cliente atualizado.
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> excluirCliente(@PathVariable String cpf) {
        service.excluirCliente(cpf);
        return ResponseEntity.noContent().build();
        // Define um endpoint para excluir um cliente pelo CPF usando DELETE /clientes/{cpf}. O CPF vem na URL (@PathVariable).
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Optional<Cliente>> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.buscarPorCpf(cpf));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}