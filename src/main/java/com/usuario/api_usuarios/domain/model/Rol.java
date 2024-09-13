package com.usuario.api_usuarios.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Rol {

    private Long id;
    private String nombreRol;
    private String descripcion; // Asegúrate de que la clase Usuario esté en el paquete correcto

}
