package com.javeriana.proyect2.controllers;

import com.javeriana.proyect2.model.LoginRequest;
import com.javeriana.proyect2.model.User;
import com.javeriana.proyect2.services.SessionManager;
import com.javeriana.proyect2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionManager sessionManager;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.login(loginRequest.getusername(), loginRequest.getpassword());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            sessionManager.login(user); // Loguea al usuario en la sesión

            // Crear el token (aquí se genera un token de ejemplo, cámbialo por la implementación real)
            String token = "GENERATED_TOKEN"; // Reemplaza esto por la lógica real de generación de token

            // Crear respuesta con token y userId
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", user.getId()); // Incluye el `userId` en la respuesta

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        sessionManager.logout(); // Lógica para cerrar sesión
        return ResponseEntity.ok("Logout exitoso");
    }
}
