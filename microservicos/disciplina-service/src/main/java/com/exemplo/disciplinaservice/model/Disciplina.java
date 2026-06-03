package com.exemplo.disciplinaservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "disciplina")
@Getter
@Setter
@AllArgsConstructor
public class Disciplina {

    private Long id;
    private String nome;
    private boolean ativo;

    public Disciplina(String nome, boolean ativo) {
        this.nome = nome;
        this.ativo = ativo;
    }
}
