package com.exemplo.crudmongo.config;

import com.exemplo.crudmongo.Model.Matricula;
import com.exemplo.crudmongo.repository.MatriculaRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;

@Configuration
public class MatriculaDataLoader {
    @Bean
    CommandLineRunner initMatricula(MatriculaRepository repository) {
        return args -> {
            Faker faker = new Faker();
            for (int i = 0; i < 10; i++) {
                Matricula m = new Matricula();
                m.setPessoaId(faker.number().randomNumber());
                m.setCursoId(faker.number().randomNumber());
                m.setDataMatricula(LocalDate.now().minusDays(faker.number().numberBetween(1, 1000)));
                m.setAtivo(faker.bool().bool());
                repository.save(m);
            }
        };
    }
}
