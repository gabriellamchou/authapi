package com.vinoteca.authapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinoteca.authapi.domain.Usuario;
import com.vinoteca.authapi.dtos.UsuarioDto;
import com.vinoteca.authapi.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario addUsuario(Usuario user) {
        return this.usuarioRepository.save(user);
    }

    public List<Usuario> getUsuarios() {
        return this.usuarioRepository.findAll();
    }

    public Usuario getUsuario(String email) {
        return this.usuarioRepository.findByEmail(email).orElse(null);
    }

    public UsuarioDto login(String email, String password) {
        Usuario usuario = this.usuarioRepository.findByEmailAndPassword(email, password).orElse(null);
        if (usuario == null) {
            return null;
        }
        UsuarioDto usuarioDto = new UsuarioDto(usuario.getId(), usuario.getUsername(), usuario.getEmail(), usuario.getRol());
        return usuarioDto;
    }
}
