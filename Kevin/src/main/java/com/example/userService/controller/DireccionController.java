package com.example.userService.controller;

import com.example.userService.model.Direccion;
import com.example.userService.model.Usuario;
import com.example.userService.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/direcciones")
public class DireccionController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> agregarDireccion(@Valid @RequestBody Direccion direccion, Authentication authentication) {
        try {
            String email = authentication.getName();
            Usuario usuario = usuarioService.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            
            direccion.setUsuario(usuario);
            usuario.getDirecciones().add(direccion);
            usuarioService.saveUser(usuario);
            
            return ResponseEntity.ok(Map.of(
                "message", "Dirección agregada exitosamente",
                "direccionId", direccion.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Error al agregar dirección",
                "message", e.getMessage()
            ));
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerDirecciones(Authentication authentication) {
        try {
            String email = authentication.getName();
            Usuario usuario = usuarioService.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            
            return ResponseEntity.ok(usuario.getDirecciones());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Error al obtener direcciones",
                "message", e.getMessage()
            ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDireccion(@PathVariable Long id, Authentication authentication) {
        try {
            String email = authentication.getName();
            Usuario usuario = usuarioService.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            
            boolean removed = usuario.getDirecciones().removeIf(d -> d.getId().equals(id));
            
            if (removed) {
                usuarioService.saveUser(usuario);
                return ResponseEntity.ok(Map.of(
                    "message", "Dirección eliminada exitosamente"
                ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "error", "Dirección no encontrada"
                ));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Error al eliminar dirección",
                "message", e.getMessage()
            ));
        }
    }
}