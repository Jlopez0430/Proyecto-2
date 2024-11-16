package com.javeriana.proyect2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Recordatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private LocalDateTime fechaRecordatorio;

    @ManyToOne
    @JoinColumn(name = "calendario_id", nullable = false)
    private Calendario calendario;

    // Constructor
    public Recordatorio(String descripcion, LocalDateTime fechaRecordatorio, Calendario calendario) {
        this.descripcion = descripcion;
        this.fechaRecordatorio = fechaRecordatorio;
        this.calendario = calendario;
    }

    // Getters y Setters generados por Lombok
    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaRecordatorio() {
        return fechaRecordatorio;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaRecordatorio(LocalDateTime fechaRecordatorio) {
        this.fechaRecordatorio = fechaRecordatorio;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }
}
