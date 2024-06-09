package com.vinoteca.authapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(usuario);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse registro(RegistroRequest request) {
        Usuario usuario = Usuario.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .username(request.getUsername())
            .rol(Rol.USUARIO)
            .build();

        this.usuarioRepository.save(usuario);

        return AuthResponse.builder()
            .token(jwtService.getToken(usuario))
            .build();
    }
}
