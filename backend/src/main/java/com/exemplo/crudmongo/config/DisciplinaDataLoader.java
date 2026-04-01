package com.exemplo.crudmongo.config;

import com.exemplo.crudmongo.Model.Disciplina;
import com.exemplo.crudmongo.repository.DisciplinaRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DisciplinaDataLoader {
    @Bean
    CommandLineRunner initDisciplina(DisciplinaRepository repository) {
        return args -> {
            Faker faker = new Faker();
            for (int i = 0; i < 10; i++) {
                Disciplina d = new Disciplina();
                d.setNome(faker.educator().course());
                d.setCargaHoraria(faker.number().numberBetween(30, 120));
                d.setAtivo(faker.bool().bool());
                repository.save(d);
            }
        };
    }
}
