package com.javeriana.proyect2.controllers;

import com.javeriana.proyect2.model.Calendario;
import com.javeriana.proyect2.services.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioController {

    private final CalendarioService calendarioService;

    @Autowired
    public CalendarioController(CalendarioService calendarioService) {
        this.calendarioService = calendarioService;
    }

    // Crear un evento con opción de recordatorio
    @PostMapping("/{userId}")
    public ResponseEntity<?> createCalendario(
            @PathVariable Long userId,
            @RequestBody Calendario calendario,
            @RequestParam boolean recordatorio) {
        try {
            Calendario newCalendario = calendarioService.createCalendario(userId, calendario, recordatorio);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCalendario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // Actualizar un evento con lógica para recordatorio
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCalendario(
            @PathVariable Long id,
            @RequestBody Calendario updatedCalendario,
            @RequestParam boolean actualizarRecordatorio) {
        try {
            Calendario updated = calendarioService.updateCalendario(id, updatedCalendario, actualizarRecordatorio);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // Eliminar un evento y su recordatorio
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCalendario(@PathVariable Long id) {
        try {
            calendarioService.deleteCalendario(id);
            return ResponseEntity.ok("Evento y recordatorio eliminados");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
