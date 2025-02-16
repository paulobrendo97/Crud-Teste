package com.CrudClinica.repository;

import com.CrudClinica.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; 
import java.util.Optional; 

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Optional<Cliente> findByCpf(String cpf); 
    
    List<Cliente> findByEspecialTrue(); // Método para filtrar clientes especiais
}
