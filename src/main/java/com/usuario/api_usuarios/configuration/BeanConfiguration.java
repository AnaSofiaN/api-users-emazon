package com.usuario.api_usuarios.configuration;

import com.usuario.api_usuarios.adapters.driven.jpa.mysql.adapter.UsuarioAdapter;
import com.usuario.api_usuarios.adapters.driven.jpa.mysql.repository.IUsuarioRepository;
import com.usuario.api_usuarios.adapters.driven.jpa.mysql.mapper.IUsuarioEntityMapper;
import com.usuario.api_usuarios.domain.api.usecase.UsuarioUseCase;
import com.usuario.api_usuarios.domain.spi.IUsuarioPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UsuarioUseCase usuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort, BCryptPasswordEncoder passwordEncoder) {
        return new UsuarioUseCase(usuarioPersistencePort, passwordEncoder);
    }

    @Bean
    public IUsuarioPersistencePort usuarioPersistencePort(IUsuarioRepository usuarioRepository, IUsuarioEntityMapper usuarioEntityMapper) {
        return new UsuarioAdapter(usuarioRepository, usuarioEntityMapper);
    }
}
