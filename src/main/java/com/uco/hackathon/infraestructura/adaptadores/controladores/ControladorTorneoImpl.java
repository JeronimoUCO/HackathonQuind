package com.uco.hackathon.infraestructura.adaptadores.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uco.hackathon.dominio.Torneo;
import com.uco.hackathon.infraestructura.adaptadores.dto.TorneoDto;
import com.uco.hackathon.infraestructura.configuracion.excepciones.MensajesError;
import com.uco.hackathon.infraestructura.configuracion.excepciones.ValidacionExcepcion;
import com.uco.hackathon.infraestructura.puertos.controladores.ControladorTorneo;
import com.uco.hackathon.infraestructura.puertos.repositorios.RepositorioTorneo;


@RestController
@RequestMapping(value="/torneo",produces = MediaType.APPLICATION_JSON_VALUE)
public class ControladorTorneoImpl implements ControladorTorneo{

    @Autowired
    private RepositorioTorneo repositorioTorneo;

    @Override
    @GetMapping
    public List<Torneo> listar() {
        Iterable<Torneo> torneosIterable = repositorioTorneo.findAll();
        return StreamSupport.stream(torneosIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @PostMapping
    public Torneo crear(@RequestBody TorneoDto torneo) {
        nombreExiste(torneo.getNombre());
        Torneo torneoPersistido=Torneo.crear(torneo.getNombre(), torneo.getUbicacion(),torneo.getDeporte(),torneo.getDescripcion());
        return repositorioTorneo.save(torneoPersistido);
    }

    @Override
    @PutMapping("/{id}")
    public void editar(@PathVariable Long id, @RequestBody TorneoDto torneo) {
        Optional<Torneo> consultaTorneo=repositorioTorneo.findById(torneo.getNombre());
        if(consultaTorneo.isPresent()){
            Torneo torneoEditado=consultaTorneo.get().editar(torneo.getNombre(), torneo.getUbicacion(),torneo.getDeporte(),torneo.getDescripcion());
            repositorioTorneo.save(torneoEditado);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        Optional<Torneo> consultaTorneo=repositorioTorneo.findById(id);
        if(consultaTorneo.isPresent()){
            Torneo torneoPersistencia=consultaTorneo.get().eliminar();
            repositorioTorneo.save(torneoPersistencia);
        }
    }

    private void nombreExiste(String nombre){
        if(repositorioTorneo.findById(nombre).isPresent()){
            throw new ValidacionExcepcion(MensajesError.NOMBRE_DE_TORNEO_EXISTENTE.getMensaje());
        };
    }

}
