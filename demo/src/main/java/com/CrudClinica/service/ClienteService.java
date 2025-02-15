package com.CrudClinica.service;

import com.CrudClinica.model.Cliente; //Representa a entidade Cliente.
import com.CrudClinica.repository.ClienteRepository; //Interface que interage com o banco de dados.
import org.springframework.stereotype.Service; // Marca a classe como um serviço do Spring (camada de negócio).
import org.springframework.transaction.annotation.Transactional; //Garante que as operações no banco sejam executadas como uma transação.

import java.util.List; //Estruturas de dados utilizadas na busca de clientes.
import java.util.Optional; //Estruturas de dados utilizadas na busca de clientes.

@Service  // Define a classe como um serviço do Spring, permitindo que seja injetada em outras partes do sistema.
public class ClienteService {
    
    private final ClienteRepository repository; // Declara o repositório que permitirá acesso ao banco de dados.

    public ClienteService(ClienteRepository repository) {
        this.repository = repository; // Construtor da classe, onde o repositório é injetado automaticamente pelo Spring.
    }

    @Transactional
    public Cliente cadastrarCliente(String cpf, String nome, String email, boolean especial) { //Método para cadastrar um cliente, que recebe CPF, nome, e-mail e a indicação se ele é especial.
        if (repository.findByCpf(cpf).isPresent()) {
            throw new RuntimeException("CPF já cadastrado!");
        }
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setEspecial(especial);

        return repository.save(cliente); // Salva o cliente no banco de dados e retorna o objeto salvo.
    }

    @Transactional
    public Cliente atualizarEmail(String cpf, String novoEmail) {
        Cliente cliente = repository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setEmail(novoEmail);
        return repository.save(cliente);
    }

    public void excluirCliente(String cpf) {// Verifica se o cliente existe antes de tentar excluí-lo.
        if (!repository.existsById(cpf)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        repository.deleteById(cpf);
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {//usca um cliente pelo CPF e retorna um Optional<Cliente>, que pode conter um valor ou estar vazio.
        return repository.findByCpf(cpf);
    }

    public List<Cliente> listarTodos() {// Busca e retorna todos os clientes cadastrados no banco.
        return repository.findAll();
    }
}

