package com.javeriana.proyect2.controllers;
import com.javeriana.proyect2.model.Calendario;
import com.javeriana.proyect2.model.User;
import com.javeriana.proyect2.services.CalendarioService;
import com.javeriana.proyect2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioController {

    private final UserService userService;
    private CalendarioService calendarioService;

    public CalendarioController(CalendarioService calendarioService, UserService userService) {
        this.calendarioService = calendarioService;
        this.userService = userService;
    }

    // Crear un nuevo calendario
    @PostMapping("/{userId}")
    public ResponseEntity<?> createCalendario(@PathVariable Long userId, @RequestBody Calendario calendario) {
        try {
            // Validar existencia del usuario
            Optional<User> user = userService.getUserById(userId);
            User user1 = userService.getUserId(userId);
            if (!user.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }

            // Asociar el calendario al usuario
            calendario.setUser(user1);
            calendario.setUserid(userId);
            Calendario newCalendario = calendarioService.createCalendario(calendario);

            return ResponseEntity.status(HttpStatus.CREATED).body(newCalendario);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el calendario: " + e.getMessage());
        }
    }

    // Obtener todos los calendarios
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getAllCalendarios(@PathVariable Long id) throws Exception {
//        Calendario cal = new Calendario();
//        cal.setName("a");
//        cal.setImportancia("b");
//        cal.setHora("c");
//        cal.setFecha("d");
//        cal.setDescripcion("e");
//        cal.setUserid(id);
//        // Obtener los calendarios filtrados por userid
//        List<Calendario> calendarios = new ArrayList<>();
//        calendarios.add(cal);
        // Obtener los calendarios filtrados por userid
        List<Calendario> calendarios = userService.getCalendariosByUserId(id);
        return ResponseEntity.ok(calendarios);
    }


    // Obtener un calendario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCalendarioById(@PathVariable Long id) {
        try {
            Calendario calendario = calendarioService.getCalendarioById(id);
            return ResponseEntity.ok(calendario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Actualizar un calendario existente
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCalendario(@PathVariable Long id, @RequestBody Calendario updatedCalendario) {
        try {
            Calendario updated = calendarioService.updateCalendario(id, updatedCalendario);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el calendario: " + e.getMessage());
        }
    }

    // Eliminar un calendario
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCalendario(@PathVariable Long id) {
        try {
            calendarioService.deleteCalendario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el calendario: " + e.getMessage()); //if it is an error its that the calendar does not exist
        }
    }
}