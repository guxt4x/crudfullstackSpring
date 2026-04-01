package com.exemplo.crudmongo.config;

import com.exemplo.crudmongo.Model.Turma;
import com.exemplo.crudmongo.repository.TurmaRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TurmaDataLoader {
    @Bean
    CommandLineRunner initTurma(TurmaRepository repository) {
        return args -> {
            Faker faker = new Faker();
            for (int i = 0; i < 10; i++) {
                Turma t = new Turma();
                t.setNome(faker.letterify("Turma ???"));
                t.setAno(faker.number().numberBetween(2020, 2026));
                t.setAtivo(faker.bool().bool());
                repository.save(t);
            }
        };
    }
}
