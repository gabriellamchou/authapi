package com.vinoteca.authapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinoteca.authapi.domain.Rol;
import com.vinoteca.authapi.domain.Usuario;
import com.vinoteca.authapi.repositories.UsuarioRepository;
import com.vinoteca.authapi.requests.AuthResponse;
import com.vinoteca.authapi.requests.LoginRequest;
import com.vinoteca.authapi.requests.RegistroRequest;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private JwtService jwtService;

    public Usuario addUsuario(Usuario user) {
        return this.usuarioRepository.save(user);
    }

    public List<Usuario> getUsuarios() {
        return this.usuarioRepository.findAll();
    }

    public Usuario getUsuario(String email) {
        return this.usuarioRepository.findByEmail(email).orElse(null);
    }

    public AuthResponse login(LoginRequest request) {
        // Usuario usuario = this.usuarioRepository.findByEmailAndPassword(email, password).orElse(null);
        // if (usuario == null) {
        //     return null;
        // }
        // UsuarioDto usuarioDto = new UsuarioDto(usuario.getId(), usuario.getUsername(), usuario.getEmail(), usuario.getRol());
        return null;
    }

    public AuthResponse registro(RegistroRequest request) {
        Usuario usuario = Usuario.builder()
            .email(request.getEmail())
            .password(request.getEmail())
            .username(request.getUsername())
            .rol(Rol.USUARIO)
            .build();

        this.usuarioRepository.save(usuario);

        return AuthResponse.builder()
            .token(jwtService.getToken(usuario))
            .build();
    }
}
