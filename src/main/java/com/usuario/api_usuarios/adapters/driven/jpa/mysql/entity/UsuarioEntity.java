package com.usuario.api_usuarios.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String documentoDeIdentidad;

    @Column(nullable = false)
    private String celular;

    @Column(nullable = false)
    private String contraseña;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private LocalDate fecha_creacion;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity rolEntity;

    // Método adicional para la verificación de edad y validaciones deben estar en la capa de servicio
}
