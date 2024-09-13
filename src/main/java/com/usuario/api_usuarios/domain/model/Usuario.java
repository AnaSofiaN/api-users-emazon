package com.usuario.api_usuarios.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    private Long id_usuario;
    private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private String fecha_creacion;
    private Rol rol; // Asegúrate de que la clase Rol esté en el paquete correcto

}
