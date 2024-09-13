package com.usuario.api_usuarios.adapters.driven.jpa.mysql.adapter;

import com.usuario.api_usuarios.adapters.driven.jpa.mysql.repository.IUsuarioRepository;
import com.usuario.api_usuarios.domain.model.Usuario;
import com.usuario.api_usuarios.domain.spi.IUsuarioPersistencePort;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioAdapter implements IUsuarioPersistencePort {

    private final IUsuarioRepository usuarioRepository;

    @Override
    public void SaveUsuario(Usuario usuario) {

    }
}
