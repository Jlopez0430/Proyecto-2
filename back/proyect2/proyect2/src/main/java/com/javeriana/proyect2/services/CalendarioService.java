package com.javeriana.proyect2.services;

import com.javeriana.proyect2.model.Calendario;
import com.javeriana.proyect2.model.User;
import com.javeriana.proyect2.repository.CalendarioRepository;
import com.javeriana.proyect2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.javeriana.proyect2.model.Calendario;
import com.javeriana.proyect2.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarioService {

    private  UserService userService;
    private final CalendarioRepository calendarioRepository;
    private final SessionManager sessionManager;

    @Autowired
    public CalendarioService(CalendarioRepository calendarioRepository, SessionManager sessionManager, UserService userService) {
        this.calendarioRepository = calendarioRepository;
        this.sessionManager = sessionManager;
        this.userService = userService;
    }

    public Calendario createCalendario(Calendario calendario) throws Exception {
        try {
            // Guardar el calendario en el repositorio
            return calendarioRepository.save(calendario);
        } catch (Exception e) {
            throw new Exception("Error al guardar el calendario: " + e.getMessage());
        }
    }

    public List<Calendario> getCalendariosByUserId(Long userId) throws Exception {
        // Verificar si el usuario está logueado

        // Buscar los calendarios filtrados por userid
        return calendarioRepository.findByUserid(userId);
    }


    public Calendario getCalendarioById(Long id) throws Exception {
        if (!sessionManager.isLoggedIn()) {
            throw new Exception("Debe iniciar sesión para ver un calendario.");
        }

        return calendarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Calendario no encontrado con ID: " + id));
    }

    // Nuevo método: Actualizar un calendario existente
    public Calendario updateCalendario(Long id, Calendario updatedCalendario) throws Exception {
        Calendario existingCalendario = calendarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Calendario no encontrado con ID: " + id));

        // Actualiza los campos necesarios (aquí puedes modificar según tus necesidades)
        existingCalendario.setName(updatedCalendario.getName());
        existingCalendario.setDescripcion(updatedCalendario.getDescripcion());
        existingCalendario.setFecha(updatedCalendario.getFecha());
        existingCalendario.setHora(updatedCalendario.getHora());
        existingCalendario.setImportancia(updatedCalendario.getImportancia());

        return calendarioRepository.save(existingCalendario);
    }

    // Nuevo método: Eliminar un calendario
    public void deleteCalendario(Long id) throws Exception {
        User user1 = userService.getUserId(id);
        Calendario calendario = user1.searchById(id);
        user1.removeCalendario(calendario);
        calendario = calendarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Calendario no encontrado con ID: " + id));

        calendarioRepository.delete(calendario);
    }
}