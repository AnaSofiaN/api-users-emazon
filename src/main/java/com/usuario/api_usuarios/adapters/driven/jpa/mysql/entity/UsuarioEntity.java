package com.usuario.api_usuarios.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private String fecha_creacion;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity rolEntity;
}
