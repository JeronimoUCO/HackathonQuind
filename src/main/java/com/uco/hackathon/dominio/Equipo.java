package com.uco.hackathon.dominio;

import com.uco.hackathon.infraestructura.configuracion.excepciones.MensajesError;
import com.uco.hackathon.infraestructura.configuracion.excepciones.ValidacionExcepcion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Equipo {
    
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer numeroJugadores;
    
    @ManyToOne
    private Torneo torneo;


    private Equipo(String nombre, String descripcion, Integer numeroJugadores, Torneo torneo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.numeroJugadores = numeroJugadores;
        this.torneo = torneo;
    }

    public Equipo() {
    }

    public static Equipo crear(String nombre, String descripcion, int numeroJugadores, Torneo torneo) {
        Equipo equipo=new Equipo(nombre,descripcion,numeroJugadores,torneo);     
        equipo.validar();
        return equipo;
    }

    public Equipo editar(String nombre, String descripcion, int numeroJugadores, Torneo torneo) {
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.numeroJugadores=numeroJugadores;
        this.torneo=torneo;
        this.validar();
        return this;
    }

    public Equipo eliminar(){
        this.nombre=null;
        this.descripcion=null;
        this.numeroJugadores=null;
        this.torneo=null;
        return this;
    }

    private void noTienePropiedadesDefinidas(){
        if(nombre == null && descripcion == null && numeroJugadores == null && torneo == null){
            throw new ValidacionExcepcion(MensajesError.EQUIPO_NO_TIENE_PROPIEDADES_DEFINIDAS.getMensaje());
        } ;
    }

    public void validar(){
        noTienePropiedadesDefinidas();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getNumeroJugadores() {
        return numeroJugadores;
    }

    public Torneo getTorneo() {
         return torneo; 
    }
    
}
