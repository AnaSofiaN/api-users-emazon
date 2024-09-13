package com.usuario.api_usuarios.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private String celular;
    private LocalDate fechaCreacion;
    private String documentoDeIdentidad;
}