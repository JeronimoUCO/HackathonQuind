package com.uco.hackathon.infraestructura.adaptadores.dto;

public class TorneoDto {
    private String nombre;
    private String ubicacion;
    private String deporte;
    private String descripcion;

    public TorneoDto(String nombre, String ubicacion, String deporte, String descripcion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.deporte = deporte;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
