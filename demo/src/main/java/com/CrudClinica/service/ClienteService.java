package com.CrudClinica.service;

import com.CrudClinica.model.Cliente;
import com.CrudClinica.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ClienteService {
    
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Cliente cadastrarCliente(String cpf, String nome, String email) {
        // Verifica se o CPF já está cadastrado
        if (repository.findByCpf(cpf).isPresent()) {
            throw new RuntimeException("CPF já cadastrado!");
        }

        // Vai gerar o número aleatorio para definir se o cliente é especial ou não
        Random random = new Random();
        int numero = random.nextInt(1000); 
        boolean especial = numero > 500;

        // Cria o cliente e define os valores
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setEspecial(especial); // Define como especial ou não

        return repository.save(cliente); // Salva o cliente no banco de dados
    }

    @Transactional
    public Cliente atualizarEmail(String cpf, String novoEmail) {
        Cliente cliente = repository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Impede a alteração do nome ou CPF
        if (!cliente.getEmail().equals(novoEmail)) {
            cliente.setEmail(novoEmail); // Atualiza somente o email
        }

        return repository.save(cliente);
    }

    public void excluirCliente(String cpf) {
        if (!repository.existsById(cpf)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        repository.deleteById(cpf);
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public List<Cliente> listarTodos() {
        return repository.findAll(); // Retorna todos os clientes
    }

    public List<Cliente> listarTodosEspeciais() {
        return repository.findByEspecialTrue(); // Filtra clientes especiais
    }
}
