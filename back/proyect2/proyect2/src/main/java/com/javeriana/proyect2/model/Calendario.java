package com.javeriana.proyect2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;           // Nombre del evento
    private String descripcion;    // Descripción del evento
    private String fecha;          // Fecha del evento
    private String hora;           // Hora del evento
    private String importancia;    // Nivel de importancia del evento

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;             // Relación con el usuario propietario del calendario

    private Long userid;           // Campo adicional para almacenar el ID del usuario

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recordatorio_id", referencedColumnName = "id")
    private Recordatorio recordatorio; // Relación con el recordatorio asociado

    // Métodos de acceso y modificación

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setRecordatorio(Recordatorio recordatorio) {
        this.recordatorio = recordatorio;
    }

    public Recordatorio getRecordatorio() {
        return recordatorio;
    }

    public void removeRecordatorio() {
        this.recordatorio = null; // Elimina la referencia al recordatorio asociado
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }

    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }

    public String getImportancia() {
        return importancia;
    }
}