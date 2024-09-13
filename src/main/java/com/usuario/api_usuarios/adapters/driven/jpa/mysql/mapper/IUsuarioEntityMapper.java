package com.usuario.api_usuarios.adapters.driven.jpa.mysql.mapper;

import com.usuario.api_usuarios.adapters.driven.jpa.mysql.entity.UsuarioEntity;
import com.usuario.api_usuarios.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUsuarioEntityMapper {

    @Mapping(target = "contraseña", source = "contraseña")
    UsuarioEntity toEntity(Usuario usuario);

    Usuario toModel(UsuarioEntity usuarioEntity);
}
