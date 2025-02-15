package com.CrudClinica.model;

import jakarta.persistence.Entity; 
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Cliente {

    @Id
    // Vamos usar o CPF como identificador único (não gerado automaticamente)
    private String cpf;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String email;

    private boolean especial; // Indica se o cliente é especial ou não

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }
}