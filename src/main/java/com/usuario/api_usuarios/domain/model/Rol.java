package com.usuario.api_usuarios.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rol {

    private Long id_rol;
    private String nombre_rol;
    private String descripcion;
    private List<Usuario> usuarios; // Asegúrate de que la clase Usuario esté en el paquete correcto

}
