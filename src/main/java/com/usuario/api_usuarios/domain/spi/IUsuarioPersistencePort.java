package com.usuario.api_usuarios.domain.spi;

import com.usuario.api_usuarios.domain.model.Usuario;

public interface IUsuarioPersistencePort {
    void SaveUsuario(Usuario usuario);
}
