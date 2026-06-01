package com.exemplo.pessoaservice.repository;

import com.exemplo.pessoaservice.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Reposit�rio JPA do microservi�o de Matr�culas.
 * Herda todos os m�todos CRUD de JpaRepository.
 */
@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    // Consulta customizada: buscar todas as matr�culas de uma pessoa
    List<Matricula> findByPessoaId(Long pessoaId);

    // Consulta customizada: buscar todas as matr�culas de um curso
    List<Matricula> findByCursoId(Long cursoId);

    // Consulta customizada: buscar matr�culas ativas
    List<Matricula> findByAtivo(boolean ativo);
}
