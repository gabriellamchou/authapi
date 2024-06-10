package com.vinoteca.authapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinoteca.authapi.domain.Usuario;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    public Optional<Usuario> findByUsername(String username);

    public Optional<Usuario> findByUsernameAndPassword(String username, String password);

}
