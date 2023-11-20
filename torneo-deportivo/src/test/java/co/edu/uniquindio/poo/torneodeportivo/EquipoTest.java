/**
 * Clase para probar el registro de los equipos
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import co.edu.uniquindio.poo.TipoTorneoGenero;

public class EquipoTest {
    /**
     * Instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(EquipoTest.class.getName());

    /**
     * Verificar que sea posible registrar un equipo en el torneo
     * 
     */
    @Test
    /*
     * verificar que si un equipo es de genero Maculino todos sos integrantes deben
     * ser de genero masculino y recuperacion de datos de los jugadores
     */
    public void ValidarGeneroMasculino() {
        LOG.info("Inicio de prueba validacion de genero Masculino...");
        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300", Genero.MASCULIONO);
        var equipo = new Equipo("Uniquindio", representante, Genero.MASCULIONO);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",
                Genero.MASCULIONO,
                LocalDate.now().minusYears(21));
        var jugador1 = new Jugador("JUAN", "Candela", "chrcandela@email.com", "6067431234",
                Genero.MASCULIONO, LocalDate.now().minusYears(21));
        equipo.registrarJugador(jugador);
        equipo.registrarJugador(jugador1);
        assertEquals(2, equipo.getJugadores().size());
        assertEquals(Genero.MASCULIONO, equipo.GeneroMasculino());

    }

    /* Validar datos con geenero femenino */
    @Test
    public void ValidarGeneroFemenino() {
        LOG.info("Inicio de prueba validacion de genero Masculino...");
        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300", Genero.MASCULIONO);
        var equipo = new Equipo("Uniquindio", representante, Genero.FEMENINO);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",
                Genero.FEMENINO,
                LocalDate.now().minusYears(21));
        var jugador1 = new Jugador("JUAN", "Candela", "chrcandela@email.com", "6067431234",
                Genero.FEMENINO, LocalDate.now().minusYears(21));
        equipo.registrarJugador(jugador);
        equipo.registrarJugador(jugador1);
        assertEquals(2, equipo.getJugadores().size());
        assertEquals(Genero.FEMENINO, equipo.GeneroFemenino());

    }

    @Test
    public void registrarEquipo() {
        LOG.info("Inicio de prueba registrarEquipo...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\|
        // fechaActual - 15 días\|fechaActual+15 días\|24\|0\|0\|LOCAL|GRUPAL}
        // Equipo{Uniquindio}
        // Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),
                LocalDate.now().plusDays(15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL,
                TipoTorneoGenero.MASCULINO);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300", Genero.MASCULIONO);

        var equipo = new Equipo("Uniquindio", representante, Genero.MASCULIONO);

        torneo.registrarParticipante(equipo);

        // Recuperación y verificación de datos
        assertTrue(torneo.getParticipantes().contains(equipo));
        assertEquals(1, torneo.getParticipantes().size());
        LOG.info("Fin de prueba registrarEquipo...");
    }

    /* Prueba */
    /**
     * Verificar que la clase Torneo valide que no se ingresen equipos con nombre
     * repetido
     * 
     */
    @Test
    public void nombreEquipoRepetido() {
        LOG.info("Inicio de prueba nombreEquipoRepetido...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\|
        // fechaActual - 15 días\|fechaActual+15 días\|24\|0\|0\|LOCAL|GRUPAL}
        // Equipo{Uniquindio}
        // Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),
                LocalDate.now().plusDays(15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL,
                TipoTorneoGenero.MASCULINO);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300", Genero.MASCULIONO);

        var equipo = new Equipo("Uniquindio", representante, Genero.MASCULIONO);
        var equipo2 = new Equipo("Uniquindio", representante, Genero.MASCULIONO);
        torneo.registrarParticipante(equipo);

        assertThrows(Throwable.class, () -> torneo.registrarParticipante(equipo2));

        LOG.info("Fin de prueba nombreEquipoRepetido...");
    }

    /**
     * Verificar que la clase Torneo valide que no se ingresen equipos cuando las
     * inscripciones ya cerraron
     * 
     */
    @Test
    public void inscripcionCerrada() {
        LOG.info("Inicio de prueba inscripcionCerrada...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\|
        // fechaActual - 15 días\|fechaActual-1 días\|24\|0\|0\|LOCAL|GRUPAL}
        // Equipo{Uniquindio}
        // Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),
                LocalDate.now().minusDays(1), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL,
                TipoTorneoGenero.MASCULINO);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300", Genero.MASCULIONO);

        var equipo = new Equipo("Uniquindio", representante, Genero.MASCULIONO);

        assertThrows(Throwable.class, () -> torneo.registrarParticipante(equipo));

        LOG.info("Fin de prueba inscripcionCerrada...");
    }

    /**
     * Verificar que la clase Torneo valide que no se ingresen equipos cuando las
     * inscripciones aun no han abrierto
     * 
     */
    @Test
    public void inscripcionNoAbierta() {
        LOG.info("Inicio de prueba inscripcionNoAbierta...");
        // Almacenar los datos de prueba Torneo{Copa Mundo\|fechaActual+ 1mes\|
        // fechaActual + 1 día\|fechaActual+15 días\|24\|0\|0\|LOCAL|GRUPAL}
        // Equipo{Uniquindio}
        // Representante{Robinson,Pulgarin,rpulgarin@email.com,6067359300}

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL, CaracterTorneo.GRUPAL,
                TipoTorneoGenero.MASCULINO);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300", Genero.MASCULIONO);

        var equipo = new Equipo("Uniquindio", representante, Genero.MASCULIONO);

        assertThrows(Throwable.class, () -> torneo.registrarParticipante(equipo));

        LOG.info("Fin de prueba inscripcionNoAbierta...");
    }
}
