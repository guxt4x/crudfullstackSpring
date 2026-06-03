package com.exemplo.pessoaservice.controller;

import com.exemplo.pessoaservice.model.Pessoa;
import com.exemplo.pessoaservice.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
@CrossOrigin(origins = "*")
public class PessoaController {

    @Autowired
    private PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole ('PROFESSOR', 'ALUNO')")
    public List<Pessoa> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/pessoas/{id}")
    @PreAuthorize("hasAnyRole ('PROFESSOR', 'ALUNO')")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome")
    @PreAuthorize("hasAnyRole ('PROFESSOR', 'ALUNO')")
    public List<Pessoa> buscarPorNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
    }

    @PostMapping
    @PreAuthorize("hasRole ('PROFESSOR')")
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
        return service.criar(pessoa);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole ('PROFESSOR')")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return service.atualizar(id, pessoa);
    }

    @PatchMapping
    @PreAuthorize("hasRole ('PROFESSOR')")
    public ResponseEntity<Void> desativar(@RequestParam Long id) {
        return service.desativar(id);
    }

    @DeleteMapping
    @PreAuthorize("hasRole ('PROFESSOR')")
    public ResponseEntity<Void> excluir(@RequestParam Long id) {
        return service.deletar(id);
    }
}
