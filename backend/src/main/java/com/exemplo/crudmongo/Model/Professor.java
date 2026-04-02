package com.exemplo.crudmongo.Model;
// Esse é o "endereço" da classe dentro do projeto (onde ela mora)

import jakarta.persistence.Entity;
// Importa algo que diz: "essa classe vai virar uma tabela no banco"

import jakarta.persistence.GeneratedValue;
// Importa algo que cria o ID automaticamente

import jakarta.persistence.GenerationType;
// Define como o ID será criado

import jakarta.persistence.Id;
// Marca qual campo é o identificador único (tipo CPF)

import jakarta.persistence.Table;
// Serve para dar nome à tabela no banco

@Entity
// Aqui estamos dizendo: essa classe é uma tabela no banco de dados

@Table(name = "professor")
// Aqui diz que o nome da tabela será "professor"

public class Professor {
// Aqui criamos o "molde" de um professor (tipo uma ficha com informações)

    @Id
    // Esse campo é o identificador único

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // O ID será criado automaticamente pelo banco

    private Long id;
    // Guarda o número único do professor

    private String nome;
    // Guarda o nome do professor

    private int idade;
    // Guarda a idade do professor

    private String email;
    // Guarda o email do professor

    private boolean ativo;
    // Guarda se o professor está ativo (true = sim, false = não)

    public Professor() {
    }
    // Esse é o construtor (serve para criar um novo professor vazio)

    public Long getId() {
    // Método para pegar o ID
        return id;
    }

    public void setId(Long id) {
    // Método para definir o ID
        this.id = id;
    }

    public String getNome() {
    // Método para pegar o nome
        return nome;
    }

    public void setNome(String nome) {
    // Método para definir o nome
        this.nome = nome;
    }

    public int getIdade() {
    // Método para pegar a idade
        return idade;
    }

    public void setIdade(int idade) {
    // Método para definir a idade
        this.idade = idade;
    }

    public String getEmail() {
    // Método para pegar o email
        return email;
    }

    public void setEmail(String email) {
    // Método para definir o email
        this.email = email;
    }

    public boolean isAtivo() {
    // Método para saber se está ativo
        return ativo;
    }

    public void setAtivo(boolean ativo) {
    // Método para definir se está ativo ou não
        this.ativo = ativo;
    }
}