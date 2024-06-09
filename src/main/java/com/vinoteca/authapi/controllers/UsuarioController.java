package com.vinoteca.authapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinoteca.authapi.domain.Rol;
import com.vinoteca.authapi.domain.Usuario;
import com.vinoteca.authapi.requests.AuthResponse;
import com.vinoteca.authapi.requests.LoginRequest;
import com.vinoteca.authapi.requests.RegistroRequest;
import com.vinoteca.authapi.services.UsuarioService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping("/usuarios")
    public List<Usuario> findAllUsuarios() {
        return this.usuarioService.getUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario findUsuarioById(@PathVariable Long id) {
        System.out.println("findById" + id);
        return new Usuario(
            id, 
            "User",
            "user@gmail.com", 
            "pass", 
            Rol.USUARIO
            );
    }

    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> addUsuario(@Valid @RequestBody RegistroRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        if ( this.usuarioService.getUsuario(request.getEmail()) != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usuarioService.registro(request));
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        if ( this.usuarioService.getUsuario(request.getEmail()) == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usuarioService.login(request));
    }
    
}
