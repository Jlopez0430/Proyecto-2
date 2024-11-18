package com.javeriana.proyect2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Recordatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean activo;

    @Column(nullable = true)
    private String fecha; // Fecha específica para el recordatorio (opcional)

    @Column(nullable = true)
    private String hora; // Hora específica para el recordatorio (opcional)

    @OneToOne(mappedBy = "recordatorio", cascade = CascadeType.ALL, orphanRemoval = true)
    private Calendario calendario;

    public Recordatorio(boolean activo) {
        this.activo = activo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }
}