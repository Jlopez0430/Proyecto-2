package com.javeriana.proyect2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Data // Esto es opcional pueden usarlo o crear los getter y setters
@NoArgsConstructor
public class Calendario {

    @Getter
    @Setter
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  // Formato: Año-Mes-Día Hora:Minuto:Segund
    private Long id;
    private String name;
    private String descripcion;
    private Date fecha;
    private String hora;
    private String importancia;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(Date fecha) {
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

    public Date getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getImportancia() {
        return importancia;
    }

}