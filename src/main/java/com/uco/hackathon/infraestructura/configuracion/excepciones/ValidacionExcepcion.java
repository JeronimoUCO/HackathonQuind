package com.uco.hackathon.infraestructura.configuracion.excepciones;

public class ValidacionExcepcion extends RuntimeException{
    public ValidacionExcepcion(String mensaje){
        super(mensaje);
    }
}
