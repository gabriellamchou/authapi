package com.vinoteca.authapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.vinoteca.authapi.dtos.UsuarioDto;
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
    public ResponseEntity<?> addUsuario(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        if ( this.usuarioService.getUsuario(usuario.getEmail()) != null) {
            return ResponseEntity.badRequest().build();
        }
        usuario.setRol(Rol.USUARIO);
        Usuario usuarioSaved = this.usuarioService.addUsuario(usuario);
        System.out.println(usuarioSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSaved);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        if ( this.usuarioService.getUsuario(usuario.getEmail()) == null) {
            return ResponseEntity.badRequest().build();
        }
        UsuarioDto usuarioDto = this.usuarioService.login(usuario.getEmail(), usuario.getPassword());
        if (usuarioDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usuarioDto);
    }
    
}
