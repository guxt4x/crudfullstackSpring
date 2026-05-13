package com.exemplo.crudmongo.controller;

import com.exemplo.crudmongo.Model.Usuario;
import com.exemplo.crudmongo.config.JwtUtil;
import com.exemplo.crudmongo.service.UsuarioService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        try {
            String username = body.get("username");
            String password = body.get("password");
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            // ← Buscar o usuário completo (com role)
            Usuario usuario = usuarioService.findByUsername(username);

            // ← Gerar token COM a role
            String token = jwtUtil.generateTokenWithRole(username, usuario.getRole());

            // ← Retornar mais informações
            return Map.of(
                    "token", token,
                    "username", username,
                    "role", usuario.getRole(),
                    "id", usuario.getId());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        String role = body.getOrDefault("role", "ROLE_USER");

        // ← Salvar e receber o usuário criado
        Usuario usuario = usuarioService.salvarUsuario(username, password, role);

        // ← Retornar os dados criados
        return Map.of(
                "message", "Usuário registrado com sucesso",
                "id", usuario.getId(),
                "username", usuario.getUsername(),
                "role", usuario.getRole());
    }

    @GetMapping("/me")
    public Map<String, Object> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Usuario usuario = usuarioService.findByUsername(username);

        return Map.of(
                "id", usuario.getId(),
                "username", usuario.getUsername(),
                "role", usuario.getRole());
    }
}
