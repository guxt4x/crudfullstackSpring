package com.exemplo.crudmongo.controller;

import com.exemplo.crudmongo.Model.Disciplina;
import com.exemplo.crudmongo.service.DisciplinaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {
    private final DisciplinaService service;
    public DisciplinaController(DisciplinaService service) { this.service = service; }
    @GetMapping
    public List<Disciplina> listarTodas() { return service.listarTodas(); }
    @PostMapping
    public Disciplina salvar(@RequestBody Disciplina disciplina) { return service.salvar(disciplina); }
    @PutMapping("/{id}")
    public Disciplina atualizar(@PathVariable Long id, @RequestBody Disciplina disciplina) { return service.atualizar(id, disciplina); }
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) { service.excluir(id); }
}
