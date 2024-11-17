package com.javeriana.proyect2.repository;

import com.javeriana.proyect2.model.Calendario;
import com.javeriana.proyect2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.List;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {

    @Query("SELECT c from Calendario c where c.user.id = :userid")
    List<Calendario> findByUserid(@Param("userid")Long userId);
}
