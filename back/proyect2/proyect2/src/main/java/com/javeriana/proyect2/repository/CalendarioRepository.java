package com.javeriana.proyect2.repository;

import com.javeriana.proyect2.model.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {

    // Método para obtener calendarios por userId
    List<Calendario> findByUserid(Long userId);

}
