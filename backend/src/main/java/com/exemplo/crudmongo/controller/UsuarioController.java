package com.exemplo.crudmongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.crudmongo.Model.Usuario;
import com.exemplo.crudmongo.service.UsuarioService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")

public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario user) {
        Usuario salvo = service.salvarUsuario(user.getUsername(), user.getPassword(), user.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

}