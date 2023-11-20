package co.edu.uniquindio.poo;
/*
 * 
Se desea poder agendar los enfrentamientos,
 indicando el lugar donde se realizará el enfrentamiento (nombre, ubicación)
 , la fecha y hora del enfrentamiento, los equipos que se enfrentarán, el juez o 
 jueces que arbitraran el encuentro, el resultado del enfrentamiento cuando haya concluido
  (puntos realizados por cada equipo) y el estado del enfrentamiento.

 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.xml.crypto.Data;

import co.edu.uniquindio.poo.torneodeportivo.Equipo;

public class Enfrentamientos {
    private LocalDate fecha;
    private LocalTime hora;
    private LugarEnfrentamiento lugarEnfrentamiento;
    private Equipo equipo1;
    private Equipo equipo2;
    private Juez juez;
    private Collection<Juez> juezs;
    private Resultado resultado;
    private int puntajeEquipo1;
    private int puntajeequipo2;
    private EstadoEnfrentamiento estadoEnfrentamiento;

    public Enfrentamientos(LocalDate fecha, LocalTime hora, LugarEnfrentamiento lugarEnfrentamiento, Equipo equipo1,
            Equipo equipo2, Juez juez, Resultado resultado, EstadoEnfrentamiento estadoEnfrentamiento) {
        this.fecha = fecha;
        this.hora = hora;
        this.lugarEnfrentamiento = lugarEnfrentamiento;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.juez = juez;
        this.resultado = resultado;
        this.estadoEnfrentamiento = estadoEnfrentamiento;
        Resultado();
    }

    public void registrarJuez(Juez juez) {
        juezs.add(juez);
    }

    public void setPuntajeEquipo1(int puntajeEquipo1) {
        this.puntajeEquipo1 = puntajeEquipo1;
    }

    public void setPuntajeequipo2(int puntajeequipo2) {
        this.puntajeequipo2 = puntajeequipo2;
    }

    public void Resultado() {

        if (resultado.EquipoGanador().equals(equipo1)) {
            setPuntajeEquipo1(3);
        } else if (resultado.EquipoGanador().equals(equipo2)) {
            setPuntajeequipo2(3);
        } else if (resultado.Empate().equals(2)) {
            setPuntajeEquipo1(1);
            setPuntajeequipo2(1);
        }

    }

    public Equipo getEquipoGanador() {
        Equipo equipo = resultado.EquipoGanador();

        return equipo;
    }

    public Equipo getEquipoPerdedor() {
        Equipo equipo = resultado.EquipoPerdedor();

        return equipo;
    }

    public ArrayList<Equipo> empate() {
        ArrayList<Equipo> equipo = resultado.Empate();

        return equipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public LugarEnfrentamiento getLugarEnfrentamiento() {
        return lugarEnfrentamiento;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public Juez getJuez() {
        return juez;
    }

    public Collection<Juez> getJuezs() {
        return juezs;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public int getPuntajeEquipo1() {
        return puntajeEquipo1;
    }

    public int getPuntajeequipo2() {
        return puntajeequipo2;
    }

}
