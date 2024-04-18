package com.uco.hackathon.infraestructura.puertos.controladores;

import java.util.List;

import com.uco.hackathon.dominio.Equipo;
import com.uco.hackathon.infraestructura.adaptadores.dto.EquipoDto;

public interface ControladorEquipo {
    List<Equipo> listar();
    Equipo crear(EquipoDto equipo);
    void editar(Long id,EquipoDto equipo);
    void eliminar(Long equipo);
}
