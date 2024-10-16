package com.javeriana.proyect2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data // Esto es opcional, pueden usarlo o crear los getter y setters
@NoArgsConstructor
public class Calendario {

    @Getter
    @Setter
    @Id // Solo una vez
    private Long id; // El id no es autogenerado

    private String name;
    private String descripcion;
    private String fecha;
    private String hora;
    private String importancia;

    // Métodos set y get, aunque se pueden omitir con @Data
    public void setId(Long id) {
        this.id = id;
    }

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
}
