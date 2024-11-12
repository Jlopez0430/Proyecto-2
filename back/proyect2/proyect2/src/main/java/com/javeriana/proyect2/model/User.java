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
    @Column(nullable = false, unique = true)
    private String userName;
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Calendario> calendarios = new ArrayList<>();

    public List<Calendario> getCalendarios() {
        return calendarios;
    }

    public void setCalendarios(List<Calendario> calendarios) {
        this.calendarios = calendarios;
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

    public String getPassword() {
        return password;
    }


    public String getUsername() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}