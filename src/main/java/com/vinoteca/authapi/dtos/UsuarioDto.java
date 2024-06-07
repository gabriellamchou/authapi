package com.vinoteca.authapi.dtos;

import com.vinoteca.authapi.domain.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioDto {
    private Long id;
    private String username;
    private String email;
    private Rol rol;
}
