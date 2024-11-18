package com.javeriana.proyect2.controllers;

import com.javeriana.proyect2.model.Calendario;
import com.javeriana.proyect2.model.User;
import com.javeriana.proyect2.repository.CalendarioRepository;
import com.javeriana.proyect2.services.CalendarioService;
import com.javeriana.proyect2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    private CalendarioService calendarioService;
    @Autowired
    private CalendarioRepository calendarioRepository;

    @PostMapping("/{userId}/calendarios")
    public ResponseEntity<?> createCalendario(@PathVariable Long userId, @RequestBody Calendario calendario) {
        try {
            Optional<User> userOptional = userService.getUserById(userId);
            if (!userOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }

            User user = userOptional.get();
            calendario.setUserid(user.getId());// Asociar el usuario con el calendario
            user.addCalendario(calendario);
            Calendario newCalendario = calendario;
            return ResponseEntity.status(HttpStatus.CREATED).body(newCalendario);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el calendario: " + e.getMessage());
        }
    }

    @PostMapping("users/{userId}")
    public ResponseEntity<?> getCalendarios(@PathVariable Long userId) {
        List<Calendario> calendarios = userService.getCalendariosByUserId(userId);
        return ResponseEntity.ok(calendarios);
    }

//    @PostMapping("/{userId}/calendarios")
//    public ResponseEntity<?> createCalendario(@PathVariable Long userId, @RequestBody Calendario calendario) {
//        try {
//            System.out.println("Buscando usuario con ID: " + userId);
//            Optional<User> userOptional = userService.getUserById(userId);
//
//            if (!userOptional.isPresent()) {
//                System.out.println("Usuario no encontrado");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
//            }
//
//            User user = userOptional.get();
//            System.out.println("Usuario encontrado: " + user.getusername());
//
//            calendario.setUserid(user.getId()); // Asociar el usuario al calendario
//            System.out.println("Creando calendario para el usuario " + user.getusername());
//
//            Calendario newCalendario = calendarioService.createCalendario(calendario);
//            System.out.println("Calendario creado con ID: " + newCalendario.getId());
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(newCalendario);
//        } catch (Exception e) {
//            e.printStackTrace(); // Imprimir el error en los logs del servidor
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el calendario: " + e.getMessage());
//        }
//    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado: " + newUser.getusername());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el registro: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
