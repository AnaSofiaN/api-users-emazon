package com.usuario.api_usuarios.domain.api.usecase;

import com.usuario.api_usuarios.domain.model.Usuario;
import com.usuario.api_usuarios.domain.spi.IUsuarioPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioUseCaseTest {

    @Mock
    private IUsuarioPersistencePort usuarioPersistencePort;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioUseCase usuarioUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUsuarioAuxBodega_Success() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setFechaCreacion(LocalDate.now());
        usuario.setContrasena("password123");
        usuario.setFechaNacimiento(LocalDate.of(2000, 1, 1)); // Mayor de edad

        when(passwordEncoder.encode("password123")).thenReturn("encryptedPassword");

        usuarioUseCase.createUsuarioAuxBodega(usuario);

        verify(usuarioPersistencePort).saveUsuario(usuario);
        assertEquals("encryptedPassword", usuario.getContrasena());
    }

    @Test
    void createUsuarioAuxBodega_InvalidEmail() {
        Usuario usuario = new Usuario();
        usuario.setEmail("invalid-email");
        usuario.setFechaCreacion(LocalDate.now());
        usuario.setContrasena("password123");
        usuario.setFechaNacimiento(LocalDate.of(2000, 1, 1)); // Mayor de edad

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioUseCase.createUsuarioAuxBodega(usuario);
        });

        assertEquals("Email inválido", thrown.getMessage());
    }

    @Test
    void createUsuarioAuxBodega_NotOfAge() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setFechaCreacion(LocalDate.now());
        usuario.setContrasena("password123");
        usuario.setFechaNacimiento(LocalDate.of(2005, 1, 1)); // Menor de edad

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioUseCase.createUsuarioAuxBodega(usuario);
        });

        assertEquals("El usuario debe ser mayor de edad", thrown.getMessage());
    }
    @Test
    void authenticate_Success() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setContrasena("encryptedPassword");

        when(usuarioPersistencePort.findUsuarioByEmail("test@example.com")).thenReturn(usuario);
        when(passwordEncoder.matches("password123", "encryptedPassword")).thenReturn(true);

        Usuario result = usuarioUseCase.authenticate("test@example.com", "password123");

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void authenticate_InvalidCredentials() {
        when(usuarioPersistencePort.findUsuarioByEmail("test@example.com")).thenReturn(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioUseCase.authenticate("test@example.com", "password123");
        });

        assertEquals("Credenciales inválidas", thrown.getMessage());
    }

    @Test
    void authenticate_InvalidPassword() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setContrasena("encryptedPassword");

        when(usuarioPersistencePort.findUsuarioByEmail("test@example.com")).thenReturn(usuario);
        when(passwordEncoder.matches("password123", "encryptedPassword")).thenReturn(false);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioUseCase.authenticate("test@example.com", "password123");
        });

        assertEquals("Credenciales inválidas", thrown.getMessage());
    }
}
