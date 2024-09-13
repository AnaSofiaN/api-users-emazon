package com.usuario.api_usuarios.adapters.driven.jpa.mysql.adapter;

import com.usuario.api_usuarios.adapters.driven.jpa.mysql.entity.UsuarioEntity;
import com.usuario.api_usuarios.adapters.driven.jpa.mysql.mapper.IUsuarioEntityMapper;
import com.usuario.api_usuarios.adapters.driven.jpa.mysql.repository.IUsuarioRepository;
import com.usuario.api_usuarios.domain.model.Usuario;
import com.usuario.api_usuarios.domain.spi.IUsuarioPersistencePort;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioAdapter implements IUsuarioPersistencePort {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioEntityMapper usuarioEntityMapper;

    @Override
    public void saveUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity = usuarioEntityMapper.toEntity(usuario);
        usuarioRepository.save(usuarioEntity);
    }

    @Override
    public Usuario findUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioEntityMapper::toModel)
                .orElse(null);
    }
}
