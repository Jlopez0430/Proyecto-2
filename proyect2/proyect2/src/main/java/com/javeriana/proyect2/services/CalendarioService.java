package com.javeriana.proyect2.services;

import com.javeriana.proyect2.model.Calendario;
import com.javeriana.proyect2.repository.CalendarioRepository;
import com.javeriana.proyect2.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarioService {
    private final CalendarioRepository calendarioRepository;
    private final UserRepository userRepository;

    @Autowired
    public CalendarioService(CalendarioRepository calendarioRepository, UserRepository userRepository) {
        this.calendarioRepository = calendarioRepository; this.userRepository = userRepository;
    }

    public Calendario createCalendario(Calendario calendario) {
        return calendarioRepository.save(calendario);
    }

    public List<Calendario> getAllCalendarios() {
        return calendarioRepository.findAll();
    }

    public Calendario getCalendarioById(Long id) {
        try {
            return calendarioRepository.findById(id)
                    .orElseThrow(() -> new Exception("Calendario not found"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;  // Puedes lanzar una excepción personalizada o retornar `null`.
        }
    }

    public Calendario updateCalendario(Long id, Calendario updatedCalendario) {
        Calendario calendario = getCalendarioById(id);
//        calendario.setName(updatedCalendario.getName());
//        calendario.setSpecies(updatedCreature.getSpecies());
//        creature.setSize(updatedCreature.getSize());
//        creature.setDangerLevel(updatedCreature.getDangerLevel());
//        creature.setHealthStatus(updatedCreature.getHealthStatus());
        return calendarioRepository.save(calendario);
    }

    public void deleteCalendario(Long id) {
        Calendario calendario = getCalendarioById(id);
//        if (!"critical".equals(creature.getHealthStatus())) {
//            creatureRepository.delete(creature);
//        } else {
//            throw new IllegalStateException("Cannot delete a creature in critical health");
//        }
    }



}
