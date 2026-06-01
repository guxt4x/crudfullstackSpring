package com.exemplo.pessoaservice.service;

import com.exemplo.pessoaservice.model.Matricula;
import com.exemplo.pessoaservice.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de neg�cio do microservi�o de Matr�culas.
 * Toda a l�gica de neg�cio fica aqui ? o controller s� delega.
 */
@Service
public class MatriculaService {

    private final MatriculaRepository repository;

    public MatriculaService(MatriculaRepository repository) {
        this.repository = repository;
    }

    /** Lista todas as matr�culas */
    public List<Matricula> listarTodas() {
        return repository.findAll();
    }

    /** Busca uma matr�cula pelo ID */
    public Optional<Matricula> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /** Lista matr�culas de uma pessoa espec�fica */
    public List<Matricula> listarPorPessoa(Long pessoaId) {
        return repository.findByPessoaId(pessoaId);
    }

    /** Lista matr�culas de um curso espec�fico */
    public List<Matricula> listarPorCurso(Long cursoId) {
        return repository.findByCursoId(cursoId);
    }

    /** Cria uma nova matr�cula */
    public Matricula salvar(Matricula matricula) {
        return repository.save(matricula);
    }

    /** Atualiza uma matr�cula existente */
    public Matricula atualizar(Long id, Matricula dados) {
        Matricula existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matr�cula n�o encontrada: " + id));
        existente.setPessoaId(dados.getPessoaId());
        existente.setCursoId(dados.getCursoId());
        existente.setDataMatricula(dados.getDataMatricula());
        existente.setAtivo(dados.isAtivo());
        return repository.save(existente);
    }

    /** Desativa uma matr�cula (soft delete) */
    public void desativar(Long id) {
        Matricula existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matr�cula n�o encontrada: " + id));
        existente.setAtivo(false);
        repository.save(existente);
    }

    /** Remove permanentemente uma matr�cula */
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
