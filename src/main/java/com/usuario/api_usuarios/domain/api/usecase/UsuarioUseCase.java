package com.usuario.api_usuarios.domain.api.usecase;


import com.usuario.api_usuarios.domain.exception.UsuarioException;
import com.usuario.api_usuarios.domain.model.Usuario;
import com.usuario.api_usuarios.domain.spi.IUsuarioPersistencePort;
import com.usuario.api_usuarios.domain.util.ErrorConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
public class UsuarioUseCase {
    private final IUsuarioPersistencePort usuarioPersistencePort;
    private final BCryptPasswordEncoder passwordEncoder;

    public void createUsuarioAuxBodega(Usuario usuario) {
        // Validar campos obligatorios
        if (!isEmailValid(usuario.getEmail())) {
            throw new UsuarioException(ErrorConstants.EMAIL_ERROR);
        }
        if (!isMayorDeEdad(usuario)) {
            throw new UsuarioException(ErrorConstants.INVALID_USER);
        }

        // Cifrar la contraseña
        String encryptedPassword = passwordEncoder.encode(usuario.getContraseña());
        usuario.setContraseña(encryptedPassword);

        // Guardar usuario con el rol 'aux_bodega'
        usuarioPersistencePort.saveUsuario(usuario);
    }

    public Usuario authenticate(String email, String password) {
        Usuario usuario = usuarioPersistencePort.findUsuarioByEmail(email);
        if (usuario == null || !passwordEncoder.matches(password, usuario.getContraseña())) {
            throw new UsuarioException(ErrorConstants.ERR_VALIDATION);
        }
        return usuario;  // Devuelve el usuario si las credenciales son correctas
    }

    private boolean isEmailValid(String email) {
        // Validación del email
        return email.matches("[^@]+@[^\\.]+\\..+");
    }

    /**
     * Verifica si el usuario es mayor de edad.
     * @param usuario El objeto Usuario que contiene la fecha de nacimiento.
     * @return true si el usuario es mayor de edad, false en caso contrario.
     */
    private boolean isMayorDeEdad(Usuario usuario) {
        // Obtener la fecha de nacimiento del usuario
        LocalDate fechaNacimiento = usuario.getFechaNacimiento();

        // Verificar que la fecha de nacimiento no sea nula
        if (fechaNacimiento == null) {
            throw new UsuarioException(ErrorConstants.DATE_INVALID);
        }

        // Obtener la fecha actual
        LocalDate hoy = LocalDate.now();

        // Calcular la diferencia en años entre la fecha actual y la fecha de nacimiento
        int edad = Period.between(fechaNacimiento, hoy).getYears();

        // Verificar si la edad es mayor o igual a 18 años
        return edad >= 18;
    }
}
