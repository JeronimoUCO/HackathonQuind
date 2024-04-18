package com.uco.hackathon.dominio;

import org.springframework.beans.factory.annotation.Autowired;

import com.uco.hackathon.infraestructura.configuracion.excepciones.MensajesError;
import com.uco.hackathon.infraestructura.configuracion.excepciones.ValidacionExcepcion;
import com.uco.hackathon.infraestructura.puertos.repositorios.RepositorioTorneo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table    
public class Torneo {
    
    @Column
    @Id
    private String nombre;
    private String ubicacion;
    private String deporte;
    private String descripcion;
        
    private Torneo(String nombre, String ubicacion, String deporte, String descripcion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.deporte = deporte;
        this.descripcion = descripcion;
    }

    public Torneo() {
    }

    public static Torneo crear(String nombre, String ubicacion, String deporte, String descripcion) {
        Torneo torneo=new Torneo(nombre,ubicacion,deporte,descripcion);     
        torneo.validar();
        return torneo;
    }

    public Torneo editar(String nombre, String ubicacion, String deporte, String descripcion) {
        this.nombre=nombre;
        this.ubicacion = ubicacion;
        this.deporte = deporte;
        this.descripcion = descripcion;
        this.validar();
        return this;
    }

    public Torneo eliminar(){
        this.nombre=null;
        this.ubicacion=null;
        this.deporte=null;
    this.descripcion=null;
    return this;
    }

    private void noTienePropiedadesDefinidas(){
        if(this.nombre == null && this.descripcion == null && this.deporte == null && this.ubicacion == null){
            throw new ValidacionExcepcion(MensajesError.EQUIPO_NO_TIENE_PROPIEDADES_DEFINIDAS.getMensaje());
        };
    }


    public void validar(){
        noTienePropiedadesDefinidas();
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDeporte() {
        return deporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    
}
