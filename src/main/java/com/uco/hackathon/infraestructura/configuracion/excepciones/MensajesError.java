package com.uco.hackathon.infraestructura.configuracion.excepciones;

public enum MensajesError {
    ERROR_GENERAL("Ha ocurrido un error inesperado"),
    NOMBRE_DE_TORNEO_EXISTENTE("Ese nombre de torneo ya existe, usa uno distinto"),
    EQUIPO_EXISTE_EN_TORNEO("Este equipo ya existe en el torneo"),
    TORNEO_INEXISTENTE("El torneo referenciado no existe"),
    EQUIPO_INEXISTENTE("El equipo que tratas de editar no existe"),
    EQUIPOS_INSUFICIENTES("No hay equipos suficientes para crear un fixture"),
    TORNEO_NO_TIENE_PROPIEDADES_DEFINIDAS("No puedes tener un torneo con propiedades nulas"),
    EQUIPO_NO_TIENE_PROPIEDADES_DEFINIDAS("No puedes tener un equipo con propiedades nulas");

    private final String mensaje;

    MensajesError(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
