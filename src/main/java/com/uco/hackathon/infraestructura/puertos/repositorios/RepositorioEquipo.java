package com.uco.hackathon.infraestructura.puertos.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uco.hackathon.dominio.Equipo;
import com.uco.hackathon.dominio.Torneo;

@Repository
public interface RepositorioEquipo extends CrudRepository<Equipo, Long> {
    Optional<Equipo> findByNombreAndTorneo(String nombreEquipo, Torneo torneo);
}
