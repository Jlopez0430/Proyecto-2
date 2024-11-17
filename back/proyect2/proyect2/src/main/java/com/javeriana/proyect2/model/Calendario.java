package com.javeriana.proyect2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String descripcion;
    private String fecha;
    private String hora;
    private String importancia;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    private Long userid;

    @OneToMany(mappedBy = "calendario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Recordatorio> recordatorios;

    // Constructor
    public Calendario(String name, String descripcion, String fecha, String hora, String importancia, User user) {
        this.name = name;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.importancia = importancia;
        this.user = user;
    }

    // Métodos para agregar recordatorios (opcional)
    public void addRecordatorio(Recordatorio recordatorio) {
        this.recordatorios.add(recordatorio);
        recordatorio.setCalendario(this); // Establecer la relación bidireccional
    }

    // Getters y Setters generados por Lombok
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getImportancia() {
        return importancia;
    }

    public List<Recordatorio> getRecordatorios() {
        return recordatorios;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public void setUser(User user){ this.user = user;}

    public void setName(String name) {
        this.name = name;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }

    public void setRecordatorios(List<Recordatorio> recordatorios) {
        this.recordatorios = recordatorios;
    }

}
