package com.exemplo.usuarioservice.config;

import com.exemplo.usuarioservice.repository.UsuarioRepository;
import com.exemplo.usuarioservice.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioDataLoader {

    @Bean
    CommandLineRunner seedUsuarios(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        return args -> {
            if (usuarioRepository.findByUsername("professor").isEmpty()) {
                usuarioService.salvarUsuario("professor", "prof123", "PROFESSOR");
                System.out.println("? Usu�rio 'professor' criado (role: PROFESSOR)");
            }
            if (usuarioRepository.findByUsername("aluno").isEmpty()) {
                usuarioService.salvarUsuario("aluno", "aluno123", "ALUNO");
                System.out.println("? Usu�rio 'aluno' criado (role: ALUNO)");
            }
        };
    }
}
