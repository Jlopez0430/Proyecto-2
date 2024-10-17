package com.javeriana.proyect2.repository;


import com.javeriana.proyect2.model.Calendario;
import com.javeriana.proyect2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {
    List<Calendario> findByUser(User user);
}