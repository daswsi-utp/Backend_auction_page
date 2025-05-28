package com.example.userService.controller;

import com.example.userService.model.Usuario;
import com.example.userService.security.JwtUtil;
import com.example.userService.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "error", "Credenciales inválidas",
                "message", e.getMessage()
            ));
        }
    }

   @PostMapping("/register")
public ResponseEntity<?> register(@Valid @RequestBody Usuario usuario) {
    try {
        if (usuarioService.findUserByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "error", "El correo ya está en uso"
            ));
        }

        Usuario nuevoUsuario = usuarioService.saveUser(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
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

    @GetMapping("/user-info")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        try {
            String email = authentication.getName();
            Usuario usuario = usuarioService.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", usuario.getId());
            userInfo.put("nombre", usuario.getNombre());
            userInfo.put("email", usuario.getEmail());
            userInfo.put("telefono", usuario.getTelefono());
            userInfo.put("dni", usuario.getDni());
            
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "error", "No autorizado",
                "message", e.getMessage()
            ));
        }
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UpdateProfileRequest request, Authentication authentication) {
        try {
            String email = authentication.getName();
            Usuario usuario = usuarioService.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            
            if (request.getNombre() != null) usuario.setNombre(request.getNombre());
            if (request.getTelefono() != null) usuario.setTelefono(request.getTelefono());
            
            usuarioService.saveUser(usuario);
            
            return ResponseEntity.ok(Map.of(
                "message", "Perfil actualizado exitosamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Error al actualizar perfil",
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