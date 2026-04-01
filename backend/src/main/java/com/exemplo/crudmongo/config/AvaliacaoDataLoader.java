package com.exemplo.crudmongo.config;

import com.exemplo.crudmongo.Model.Avaliacao;
import com.exemplo.crudmongo.repository.AvaliacaoRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;

@Configuration
public class AvaliacaoDataLoader {
    @Bean
    CommandLineRunner initAvaliacao(AvaliacaoRepository repository) {
        return args -> {
            Faker faker = new Faker();
            for (int i = 0; i < 10; i++) {
                Avaliacao a = new Avaliacao();
                a.setPessoaId(faker.number().randomNumber());
                a.setDisciplinaId(faker.number().randomNumber());
                a.setNota(faker.number().randomDouble(1, 0, 10));
                a.setData(LocalDate.now().minusDays(faker.number().numberBetween(1, 365)));
                a.setAtivo(faker.bool().bool());
                repository.save(a);
            }
        };
    }
}
