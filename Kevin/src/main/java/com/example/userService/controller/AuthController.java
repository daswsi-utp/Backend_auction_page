package com.example.userService.controller;

import com.example.userService.model.Usuario;
import com.example.userService.security.JwtUtil;
import com.example.userService.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            String token = jwtUtil.generateToken(request.getEmail());
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("email", request.getEmail());
            response.put("message", "Login exitoso");
            
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token)
                    .body(response);
                    
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Credenciales inválidas",
                "message", e.getMessage()
            ));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        try {
            if (usuarioService.findUserByEmail(usuario.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body(Map.of(
                    "error", "El correo ya está en uso"
                ));
            }

            Usuario nuevoUsuario = usuarioService.saveUser(usuario);
            return ResponseEntity.ok(Map.of(
                "message", "Usuario registrado exitosamente",
                "userId", nuevoUsuario.getId(),
                "email", nuevoUsuario.getEmail()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Error al registrar usuario",
                "message", e.getMessage()
            ));
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> testConnection() {
        return ResponseEntity.ok("Conexión exitosa con el backend - " + new java.util.Date());
    }

    @GetMapping("/check-auth")
    public ResponseEntity<?> checkAuth() {
        return ResponseEntity.ok(Map.of(
            "message", "Token válido",
            "status", "authenticated"
        ));
    }
}