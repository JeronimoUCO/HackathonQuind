package com.uco.hackathon.infraestructura.adaptadores.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



//TODO Clase de prueba para guardar la entidad
@Entity
@Table
public class Prueba {

    @Column
    @Id
    private String nombre;


    public Prueba() {
    }

    public Prueba(String nombre) {
        this.nombre = nombre;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }
}
