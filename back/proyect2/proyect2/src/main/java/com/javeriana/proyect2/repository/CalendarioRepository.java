package com.javeriana.proyect2.repository;

import com.javeriana.proyect2.model.Calendario;
import com.javeriana.proyect2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.List;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {
<<<<<<< HEAD:back/proyect2/proyect2/src/main/java/com/javeriana/proyect2/repository/CalendarioRepository.java

    // Método para obtener calendarios por userId
    List<Calendario> findByUserid(Long userId);
}
=======
    List<Calendario> findByUser(User user);
}
>>>>>>> 4b81b5343930d522ad082e3aa36782eee7fa5537:proyect2/proyect2/src/main/java/com/javeriana/proyect2/repository/CalendarioRepository.java
