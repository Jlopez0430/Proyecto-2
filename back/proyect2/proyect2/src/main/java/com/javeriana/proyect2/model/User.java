package com.javeriana.proyect2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
//@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Calendario> calendarios = new ArrayList<>();

    public List<Calendario> getCalendarios() {
        return calendarios;
    }

    public void setCalendarios(Calendario calendario) {
        this.calendarios.add(calendario);
    }

    public void addCalendario(Calendario calendario) {
        calendarios.add(calendario);
    }

    public void removeCalendario(Calendario calendario) {
        calendarios.remove(calendario);
    }

    public Calendario searchById(Long id){
        for(Calendario calendario : calendarios){
            if(calendario.getId().equals(id)){
                return calendario;
            }
        }
        return null;
    }

    public String getpassword() {
        return password;
    }


    public String getusername() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setpassword(String password) {
        this.password = password;
    }
}