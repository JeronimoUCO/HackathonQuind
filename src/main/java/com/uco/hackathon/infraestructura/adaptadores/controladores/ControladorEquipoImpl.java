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

import com.uco.hackathon.dominio.Equipo;
import com.uco.hackathon.dominio.Torneo;
import com.uco.hackathon.infraestructura.adaptadores.dto.EquipoDto;
import com.uco.hackathon.infraestructura.configuracion.excepciones.MensajesError;
import com.uco.hackathon.infraestructura.configuracion.excepciones.ValidacionExcepcion;
import com.uco.hackathon.infraestructura.puertos.controladores.ControladorEquipo;
import com.uco.hackathon.infraestructura.puertos.repositorios.RepositorioEquipo;
import com.uco.hackathon.infraestructura.puertos.repositorios.RepositorioTorneo;

@RestController
@RequestMapping(value="/equipo",produces = MediaType.APPLICATION_JSON_VALUE)
public class ControladorEquipoImpl implements ControladorEquipo{
    @Autowired
    private RepositorioEquipo repositorioEquipo;
    
    @Autowired
    private RepositorioTorneo repositorioTorneo;


    @Override
    @GetMapping
    public List<Equipo> listar() {
        Iterable<Equipo> equiposIterable = repositorioEquipo.findAll();
        return StreamSupport.stream(equiposIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @PostMapping
    public Equipo crear(@RequestBody EquipoDto equipo) {
        torneoNoExiste(equipo);
        nombreExisteEnTorneo(equipo);
        Optional<Torneo> consultaTorneo=repositorioTorneo.findById(equipo.getIdTorneo());
        Equipo equipoPersistido=Equipo.crear(equipo.getNombre(), equipo.getDescripcion(), equipo.getNumeroJugadores(), consultaTorneo.get());
        return repositorioEquipo.save(equipoPersistido);
    }

    @Override
    @PutMapping("/{id}")
    public void editar(@PathVariable Long id, @RequestBody EquipoDto equipo) {
        equipoNoExiste(equipo);
        nombreExisteEnTorneo(equipo);
        Optional<Torneo> consultaTorneo=repositorioTorneo.findById(equipo.getIdTorneo());
        Optional<Equipo> consultaEquipo=repositorioEquipo.findById(equipo.getId());
        if(consultaEquipo.isPresent() && consultaTorneo.isPresent()){
            Equipo equipoEditado=consultaEquipo.get().editar(equipo.getNombre(), equipo.getDescripcion(), equipo.getNumeroJugadores(), consultaTorneo.get());
            repositorioEquipo.save(equipoEditado);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        Optional<Equipo> consultaEquipo=repositorioEquipo.findById(id);
        if(consultaEquipo.isPresent()){
            Equipo equipoPersistencia=consultaEquipo.get().eliminar();
            repositorioEquipo.save(equipoPersistencia);
        }
    }
    
    private void nombreExisteEnTorneo(EquipoDto equipoDto){
        Torneo torneo=repositorioTorneo.findById(equipoDto.getIdTorneo()).get();
        if(repositorioEquipo.findByNombreAndTorneo(equipoDto.getNombre(), torneo).isPresent()){
            throw new ValidacionExcepcion(MensajesError.EQUIPO_EXISTE_EN_TORNEO.getMensaje());
        };
    }

    private void torneoNoExiste(EquipoDto equipo){
        if(repositorioTorneo.findById(equipo.getIdTorneo()).isEmpty()){
            throw new ValidacionExcepcion(MensajesError.TORNEO_INEXISTENTE.getMensaje());
        }}

    private void equipoNoExiste(EquipoDto equipo){
        Optional<Torneo> consultaTorneo=repositorioTorneo.findById(equipo.getIdTorneo());
        Optional<Equipo> consultaEquipo=repositorioEquipo.findById(equipo.getId());
        
        if(consultaEquipo.isPresent() || consultaTorneo.isPresent()){
            throw new ValidacionExcepcion(MensajesError.EQUIPO_INEXISTENTE.getMensaje());
        }
    }
}
