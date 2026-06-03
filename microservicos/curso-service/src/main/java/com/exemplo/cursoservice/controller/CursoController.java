package com.exemplo.cursoservice.controller;

import com.exemplo.cursoservice.model.Curso;
import com.exemplo.cursoservice.service.CursoService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public List<Curso> listarCursos() {
        return service.listAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    public Curso criar(@RequestBody Curso curso) {
        return service.save(curso);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public Curso atualizar(@RequestBody Curso curso, @PathVariable Long id) {
        return service.update(id, curso);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public void excluir(@PathVariable Long id) {
        service.delete(id);
    }
}
