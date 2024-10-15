package com.javeriana.proyect2.repository;


import com.javeriana.proyect2.model.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {
}