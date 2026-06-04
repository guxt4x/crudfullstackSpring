package com.exemplo.professorservice.config;

import com.exemplo.professorservice.model.Professor;
import com.exemplo.professorservice.repository.ProfessorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Carrega dados iniciais no banco H2 ao subir o microservico.
 */
@Configuration
public class ProfessorDataLoader {

    @Bean
    CommandLineRunner carregarDados(ProfessorRepository repository) {
        return args -> {
            repository.save(new Professor("Milton Jr", 40, "milton.jr@gmail.com", "Programacao", true));
            repository.save(new Professor("Rocha lima", 60, "rocha.lima@gmail.com", "Redes", true));
            repository.save(new Professor("Wanderson", 38, "wanderson@gmail.com", "Banco de Dados", false));
            System.out.println("[professor-service] Dados iniciais carregados.");
        };
    }
}