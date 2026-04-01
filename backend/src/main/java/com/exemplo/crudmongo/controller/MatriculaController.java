package com.exemplo.crudmongo.controller;

import com.exemplo.crudmongo.Model.Matricula;
import com.exemplo.crudmongo.service.MatriculaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    private final MatriculaService service;
    public MatriculaController(MatriculaService service) { this.service = service; }
    @GetMapping
    public List<Matricula> listarTodas() { return service.listarTodas(); }
    @PostMapping
    public Matricula salvar(@RequestBody Matricula matricula) { return service.salvar(matricula); }
    @PutMapping("/{id}")
    public Matricula atualizar(@PathVariable Long id, @RequestBody Matricula matricula) { return service.atualizar(id, matricula); }
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) { service.excluir(id); }
}
