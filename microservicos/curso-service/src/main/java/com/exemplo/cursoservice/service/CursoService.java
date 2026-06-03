package com.exemplo.cursoservice.service;

import com.exemplo.cursoservice.model.Curso;
import com.exemplo.cursoservice.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listAll() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> listById(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso update(Long id, Curso curso) {
        return cursoRepository.findById(id).map(c -> {
            c.setNome(curso.getNome());
            c.setDescricao(curso.getDescricao());
            c.setAtivo(curso.isAtivo());
            return cursoRepository.save(c);
        })
        .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }
    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }
}
