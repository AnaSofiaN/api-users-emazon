package com.usuario.api_usuarios.adapters.driven.jpa.mysql.repository;

import com.usuario.api_usuarios.adapters.driven.jpa.mysql.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
