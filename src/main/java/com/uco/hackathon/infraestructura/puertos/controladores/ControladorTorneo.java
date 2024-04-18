package com.uco.hackathon.infraestructura.puertos.controladores;

import java.util.List;

import com.uco.hackathon.dominio.Torneo;
import com.uco.hackathon.infraestructura.adaptadores.dto.TorneoDto;

public interface ControladorTorneo {
    List<Torneo> listar();
    Torneo crear(TorneoDto torneo);
    void editar(Long id,TorneoDto torneo);
    void eliminar(String id);
}
