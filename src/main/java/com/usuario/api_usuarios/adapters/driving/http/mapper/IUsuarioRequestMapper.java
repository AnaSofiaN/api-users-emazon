package com.usuario.api_usuarios.adapters.driving.http.mapper;

import com.usuario.api_usuarios.adapters.driving.http.dto.request.UsuarioRequest;
import com.usuario.api_usuarios.domain.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUsuarioRequestMapper {
    Usuario usuarioModel(UsuarioRequest usuarioRequest);
}
