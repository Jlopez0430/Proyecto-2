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
    public CalendarioController(CalendarioService  calendarioService) {
        this.calendarioService = calendarioService;
    }

    // Crear una nueva criatura
    @PostMapping
    public ResponseEntity<Calendario> createCalendario(@RequestBody Calendario calendario) {
        Calendario newCalendario = calendarioService.createCalendario(calendario);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCalendario);
    }

    // Obtener todas las criaturas
    @GetMapping
    public List<Calendario> getAllCalendarios() {
        return calendarioService.getAllCalendarios();
    }

    // Obtener una criatura por ID
    @GetMapping("/{id}")
    public Calendario getCalendarioById(@PathVariable Long id) {
        return calendarioService.getCalendarioById(id);
    }

    // Actualizar una criatura existente
    @PutMapping("/{id}")
    public Calendario updateCalendario(@PathVariable Long id, @RequestBody Calendario updatedCalendario) {
        return calendarioService.updateCalendario(id, updatedCalendario);
    }

    // Eliminar una criatura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendario(@PathVariable Long id) {
        calendarioService.deleteCalendario(id);
        return ResponseEntity.noContent().build();
    }

    // Asignar una zona a una criatura
}
