package com.usuario.api_usuarios.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String documentoDeIdentidad; // Añadido
    private String celular; // Añadido
    private String contrasena;
    private LocalDate fechaNacimiento; // Cambiado a fechaNacimiento
    private LocalDate fechaCreacion;
    private Rol rol; // Asegúrate de que la clase Rol esté en el paquete correcto

}
