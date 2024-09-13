package com.usuario.api_usuarios.adapters.driving.http.controller;

import com.usuario.api_usuarios.adapters.driving.http.dto.request.UsuarioRequest;
import com.usuario.api_usuarios.adapters.driving.http.mapper.IUsuarioRequestMapper;
import com.usuario.api_usuarios.domain.api.usecase.UsuarioUseCase;
import com.usuario.api_usuarios.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;
    private final IUsuarioRequestMapper usuarioRequestMapper;

    @PostMapping("/crear-aux-bodega")
    public ResponseEntity<String> createUsuarioAuxBodega(@RequestBody UsuarioRequest usuarioRequest) {
        try {
            Usuario usuario = usuarioRequestMapper.usuarioModel(usuarioRequest);

            if (usuario.getFechaCreacion() == null) {
                usuario.setFechaCreacion(LocalDate.now());
            }

            usuarioUseCase.createUsuarioAuxBodega(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario aux_bodega creado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/crear-cliente")
    public ResponseEntity<String> createUsuarioCliente(@RequestBody UsuarioRequest usuarioRequest) {
        try {
            Usuario usuario = usuarioRequestMapper.usuarioModel(usuarioRequest);

            if (usuario.getFechaCreacion() == null) {
                usuario.setFechaCreacion(LocalDate.now());
            }

            usuarioUseCase.createUsuarioCliente(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario cliente creado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestParam String email,
                                               @RequestParam String password) {
        try {
            Usuario usuario = usuarioUseCase.authenticate(email, password);
            return ResponseEntity.ok("Autenticación exitosa");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
