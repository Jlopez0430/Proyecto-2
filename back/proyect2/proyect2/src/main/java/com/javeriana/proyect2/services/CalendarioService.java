package com.javeriana.proyect2.services;

import com.javeriana.proyect2.model.Calendario;
import com.javeriana.proyect2.model.Recordatorio;
import com.javeriana.proyect2.model.User;
import com.javeriana.proyect2.repository.CalendarioRepository;
import com.javeriana.proyect2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarioService {

    private final CalendarioRepository calendarioRepository;
    private final UserRepository userRepository;

    @Autowired
    public CalendarioService(CalendarioRepository calendarioRepository, UserRepository userRepository) {
        this.calendarioRepository = calendarioRepository;
        this.userRepository = userRepository;
    }

    // Crear un calendario con opción de recordatorio
    public Calendario createCalendario(Long userId, Calendario calendario, boolean crearRecordatorio) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + userId));

        calendario.setUser(user);

        if (crearRecordatorio) {
            Recordatorio recordatorio = new Recordatorio(true);
            calendario.setRecordatorio(recordatorio);
        }

        return calendarioRepository.save(calendario);
    }

    // Actualizar un calendario con lógica para recordatorios
    public Calendario updateCalendario(Long id, Calendario updatedCalendario, boolean actualizarRecordatorio) throws Exception {
        Calendario existingCalendario = calendarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Calendario no encontrado con ID: " + id));

        // Actualizar los campos del calendario
        existingCalendario.setName(updatedCalendario.getName());
        existingCalendario.setFecha(updatedCalendario.getFecha());
        existingCalendario.setHora(updatedCalendario.getHora());

        // Lógica para actualizar el recordatorio
        if (actualizarRecordatorio) {
            // Si ya existe un recordatorio, eliminarlo y crear uno nuevo
            if (existingCalendario.getRecordatorio() != null) {
                existingCalendario.removeRecordatorio();
            }
            Recordatorio nuevoRecordatorio = new Recordatorio(true);
            existingCalendario.setRecordatorio(nuevoRecordatorio);
        } else {
            // Si no se quiere recordatorio, eliminar el existente (si hay)
            if (existingCalendario.getRecordatorio() != null) {
                existingCalendario.removeRecordatorio();
            }
        }

        return calendarioRepository.save(existingCalendario);
    }

    // Eliminar un calendario (y su recordatorio si existe)
    public void deleteCalendario(Long id) throws Exception {
        Calendario calendario = calendarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Calendario no encontrado con ID: " + id));

        calendarioRepository.delete(calendario); // `orphanRemoval` asegura la eliminación del recordatorio
    }
}
