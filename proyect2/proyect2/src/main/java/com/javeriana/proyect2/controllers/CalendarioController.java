package com.javeriana.proyect2.controllers;
import com.javeriana.proyect2.model.Calendario;
import com.javeriana.proyect2.services.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioController {

    private final CalendarioService calendarioService;

    @Autowired
    public CalendarioController(CalendarioService calendarioService) {
        this.calendarioService = calendarioService;
    }

    // Crear un nuevo calendario
    @PostMapping
    public ResponseEntity<?> createCalendario(@RequestBody Calendario calendario) {
        try {
            Calendario newCalendario = calendarioService.createCalendario(calendario);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCalendario);
        } catch (Exception e) {
            // Devolvemos un error 401 si el usuario no está logueado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // Obtener todos los calendarios
    @GetMapping
    public ResponseEntity<?> getAllCalendarios() {
        try {
            List<Calendario> calendarios = calendarioService.getAllCalendarios();
            return ResponseEntity.ok(calendarios);
        } catch (Exception e) {
            // Devolvemos un error 401 si el usuario no está logueado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // Obtener un calendario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCalendarioById(@PathVariable Long id) {
        try {
            Calendario calendario = calendarioService.getCalendarioById(id);
            return ResponseEntity.ok(calendario);
        } catch (Exception e) {
            // Si no encuentra el calendario, devolvemos un 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Actualizar un calendario existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCalendario(@PathVariable Long id, @RequestBody Calendario updatedCalendario) {
        try {
            Calendario updated = calendarioService.updateCalendario(id, updatedCalendario);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            // Si ocurre un error, devolvemos un 404 o 401 dependiendo del caso
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // Eliminar un calendario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCalendario(@PathVariable Long id) {
        try {
            calendarioService.deleteCalendario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Si ocurre un error, devolvemos un 404 si el calendario no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
