package com.exemplo.disciplinaservice.service;

import com.exemplo.disciplinaservice.model.Disciplina;
import com.exemplo.disciplinaservice.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository repository;

    public List<Disciplina> findAll() {
        return repository.findAll();
    }

    public Optional<Disciplina> findById(Long id) {
        return repository.findById(id);
    }

    public Disciplina save(Disciplina disciplina) {
        return repository.save(disciplina);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
