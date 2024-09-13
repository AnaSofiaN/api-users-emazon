package com.usuario.api_usuarios.domain.api.usecase;


import com.usuario.api_usuarios.domain.exception.UsuarioException;
import com.usuario.api_usuarios.domain.model.Rol;
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
        validateUsuario(usuario);

        // Cifrar la contraseña
        String encryptedPassword = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(encryptedPassword);

        // Asignar rol 'aux_bodega'
        Rol rolAuxBodega = new Rol(2L, "aux_bodega", "Usuario de bodega"); // Asegúrate de que los ID y descripciones sean correctos
        usuario.setRol(rolAuxBodega);

        // Guardar usuario con el rol 'aux_bodega'
        usuarioPersistencePort.saveUsuario(usuario);
    }

    public void createUsuarioCliente(Usuario usuario) {
        // Validar campos obligatorios
        validateUsuario(usuario);

        // Cifrar la contraseña
        String encryptedPassword = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(encryptedPassword);

        // Asignar rol 'cliente'
        Rol rolCliente = new Rol(3L, "cliente", "Usuario cliente"); // Asegúrate de que los ID y descripciones sean correctos
        usuario.setRol(rolCliente);

        // Guardar usuario con el rol 'cliente'
        usuarioPersistencePort.saveUsuario(usuario);
    }

    private void validateUsuario(Usuario usuario) {
        if (!isEmailValid(usuario.getEmail())) {
            throw new UsuarioException(ErrorConstants.EMAIL_ERROR);
        }
        if (!isTelefonoValido(usuario.getCelular())) {
            throw new UsuarioException(ErrorConstants.PHONE_ERROR);
        }
        if (!isDocumentoValido(usuario.getDocumentoDeIdentidad())) {
            throw new UsuarioException(ErrorConstants.DOCUMENT_ERROR);
        }
    }

    public Usuario authenticate(String email, String password) {
        Usuario usuario = usuarioPersistencePort.findUsuarioByEmail(email);
        if (usuario == null || !passwordEncoder.matches(password, usuario.getContrasena())) {
            throw new UsuarioException(ErrorConstants.ERR_VALIDATION);
        }
        return usuario;  // Devuelve el usuario si las credenciales son correctas
    }


    private boolean isTelefonoValido(String telefono) {
        // Validación del teléfono
        return telefono.matches("\\+?[0-9]{1,13}");
    }

    private boolean isDocumentoValido(String documento) {
        // Validación del documento de identidad
        return documento.matches("[0-9]+");
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
