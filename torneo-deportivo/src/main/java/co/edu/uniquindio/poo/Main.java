package co.edu.uniquindio.poo;

import java.time.LocalDate;

import co.edu.uniquindio.poo.torneodeportivo.CaracterTorneo;
import co.edu.uniquindio.poo.torneodeportivo.Equipo;
import co.edu.uniquindio.poo.torneodeportivo.Genero;
import co.edu.uniquindio.poo.torneodeportivo.Jugador;
import co.edu.uniquindio.poo.torneodeportivo.Persona;
import co.edu.uniquindio.poo.torneodeportivo.TipoTorneo;
import co.edu.uniquindio.poo.torneodeportivo.Torneo;

public class Main {

    public static void main(String[] args) {
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 10, 1), LocalDate.of(2023, 8, 1),
                LocalDate.of(2024, 9, 15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL,
                TipoTorneoGenero.MASCULINO);
        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300",
                Genero.MASCULIONO);
        var equipo1 = new Equipo("Uniquindio", representante, Genero.MASCULIONO);
        var equipo2 = new Equipo("Uq", representante, Genero.MASCULIONO);

        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", Genero.MASCULIONO,
                LocalDate.now().minusYears(15));
        var jugador1 = new Jugador("Martha", "Candela", "chrcandela@email.com", "6067431234", Genero.MASCULIONO,
                LocalDate.now().minusYears(15));
        var jugador3 = new Jugador("Chan", "Candela", "chrcandela@email.com", "6067431234", Genero.MASCULIONO,
                LocalDate.now().minusYears(15));
        equipo1.registrarJugador(jugador);
        equipo1.registrarJugador(jugador1);
        equipo1.registrarJugador(jugador3);

        torneo.registrarParticipante(equipo1);
        torneo.registrarParticipante(equipo2);

        System.out.println(equipo1.GeneroMasculino());

    }

}
