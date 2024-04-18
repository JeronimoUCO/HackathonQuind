package com.uco.hackathon.infraestructura.puertos.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uco.hackathon.dominio.Torneo;


@Repository
public interface RepositorioTorneo extends CrudRepository<Torneo, String> {
}
