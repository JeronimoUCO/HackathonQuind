package com.uco.hackathon.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.uco.hackathon.infraestructura.configuracion.excepciones.MensajesError;
import com.uco.hackathon.infraestructura.configuracion.excepciones.ValidacionExcepcion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Fixture {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Equipo> equipos;
    @OneToOne
    private Torneo torneo;

    private Fixture(List<Equipo> equipos, Torneo torneo) {
        this.equipos = equipos;
        this.torneo = torneo;
    }

    public Fixture crear(Torneo torneo, List<Equipo> equipos){
        Fixture fixture=new Fixture(equipos,torneo);
        fixture.validarEquipos();
        return fixture;
    }

    public String crearIda(){
        String equiposIda="";
        for (Equipo equipo : equipos) {
            for (Equipo equipo_comparado : equipos) {
                if(equipo.getNombre()!=equipo_comparado.getNombre()){
                    equiposIda+=equipo.getNombre()+" VS "+equipo_comparado.getNombre()+" ,";
                }
            }
        }
        return equiposIda;
    }
    public String crarVuelta(){
        List<Equipo> listaEquiposVuelta= new ArrayList<>(equipos);
        Collections.reverse(equipos);
        String equiposVuelta="";
        for (Equipo equipo : listaEquiposVuelta) {
            for (Equipo equipo_comparado : listaEquiposVuelta) {
                if(equipo.getNombre()!=equipo_comparado.getNombre()){
                    equiposVuelta+=equipo.getNombre()+" VS "+equipo_comparado.getNombre()+" ,";
                }
            }
        }
        return equiposVuelta;
    }
    public String crearIdaYVuelta(){
        String ida="Ida: "+crearIda();
        String vuelta="Vuelta: "+crarVuelta();
        return ida+vuelta;
    }

    private void validarEquipos(){
        if(this.equipos.size()<2){
            throw new ValidacionExcepcion(MensajesError.EQUIPOS_INSUFICIENTES.getMensaje());
        }
    }
}
