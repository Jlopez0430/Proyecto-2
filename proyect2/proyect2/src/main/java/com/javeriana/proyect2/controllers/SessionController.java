package com.javeriana.proyect2.controllers;

import com.javeriana.proyect2.model.User;
import com.javeriana.proyect2.services.SessionManager;
import com.javeriana.proyect2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    private final SessionManager sessionManager;
    private final UserService userService;

    @Autowired
    public SessionController(SessionManager sessionManager, UserService userService) {
        this.sessionManager = sessionManager;
        this.userService = userService;
    }

    // Iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        boolean loggedIn = userService.login(user.getName




                (), user.getPassword()); // Usar el nombre y la contraseña
        if (loggedIn) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    // Cerrar sesión
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        sessionManager.logout(); // Limpiar la sesión
        return ResponseEntity.ok("Sesión cerrada exitosamente");
    }

    // Obtener el usuario logueado
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser() {
        return sessionManager.getLoggedInUser()
                .map(user -> ResponseEntity.ok(user))  // Si hay un usuario logueado
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)) ; // Si no hay usuario logueado
    }

}