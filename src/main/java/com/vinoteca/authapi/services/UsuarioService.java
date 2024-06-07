package com.vinoteca.authapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinoteca.authapi.domain.Usuario;
import com.vinoteca.authapi.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario addUsuario(Usuario user) {
        return this.usuarioRepository.save(user);
    }

    public Usuario getUsuario(String email) {
        return this.usuarioRepository.findByEmail(email).orElse(null);
    }
}
