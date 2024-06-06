package com.vinoteca.authapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.vinoteca.authapi.domain.Rol;
import com.vinoteca.authapi.domain.Usuario;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    
    @GetMapping("/usuarios")
    public List<Usuario> findAll() {
        System.out.println("findAll");
        return List.of(
            new Usuario(
                null, 
                "maria@gmail.com",
                "pass1",
                Rol.USUARIO),
            new Usuario(
                null, 
                "gabi@gmail.com",
                "pass2",
                Rol.ADMIN),
            new Usuario(
                null, 
                "patri@gmail.com",
                "pass3",
                Rol.USUARIO)
        );
    }

    @GetMapping("/usuarios/{id}")
    public Usuario findById(@PathVariable Long id) {
        System.out.println("findById" + id);
        return new Usuario(
            id, 
            "user@gmail.com", 
            "pass", 
            Rol.USUARIO
            );
    }

}
