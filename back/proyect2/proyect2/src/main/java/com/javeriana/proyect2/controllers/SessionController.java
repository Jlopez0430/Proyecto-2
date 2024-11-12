package com.javeriana.proyect2.controllers;

import com.javeriana.proyect2.model.LoginRequest;
import com.javeriana.proyect2.model.User;
import com.javeriana.proyect2.services.SessionManager;
import com.javeriana.proyect2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        boolean success = userService.login(loginRequest.getUserName(), loginRequest.getPassword());
        if (success) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        // Lógica de cierre de sesión (e.g., eliminar sesión)
        return ResponseEntity.ok("Logout exitoso");
    }
}
