package com.uco.hackathon.infraestructura.adaptadores.dto;

public class EquipoDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer numeroJugadores;
    private String idTorneo;

    public EquipoDto(Long id, String nombre, String descripcion, Integer numeroJugadores, String idTorneo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.numeroJugadores = numeroJugadores;
        this.idTorneo = idTorneo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(Integer numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public String getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(String idTorneo) {
        this.idTorneo = idTorneo;
    }

}
