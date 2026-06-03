package com.exemplo.pessoaservice.service;

import com.exemplo.pessoaservice.model.Pessoa;
import com.exemplo.pessoaservice.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public List<Pessoa> listarTodas() {
        return repository.findAll();
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Pessoa> buscarPorNome(String nome) {
        if (nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        return repository.findByNome(nome);
    }

    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = repository.save(pessoa);
        return new ResponseEntity<>(novaPessoa, HttpStatus.CREATED);
    }

    public ResponseEntity<Pessoa> atualizar(Long id, @RequestBody Pessoa pessoa) {
        return repository.findById(id).map(pa -> {
            pa.setNome(pessoa.getNome());
            pa.setEmail(pessoa.getEmail());
            pa.setIdade(pessoa.getIdade());
            Pessoa pessoaAtualizada = repository.save(pa);
            return new ResponseEntity<>(pessoaAtualizada, HttpStatus.OK);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deletar(Long id) {
       return repository.findById(id).map(p -> {
           repository.delete(p);
           return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
       }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> desativar(Long id) {
        return repository.findById(id).map(p -> {
            p.setAtivo(false);
            repository.save(p);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }).orElse(ResponseEntity.notFound().build());
    }
}
