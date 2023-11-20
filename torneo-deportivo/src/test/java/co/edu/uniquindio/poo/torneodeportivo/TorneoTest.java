/**
 * Clase para probar el funcionamiento del Torneo
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import co.edu.uniquindio.poo.Enfrentamientos;
import co.edu.uniquindio.poo.EstadoEnfrentamiento;
import co.edu.uniquindio.poo.Juez;
import co.edu.uniquindio.poo.LugarEnfrentamiento;
import co.edu.uniquindio.poo.Resultado;
import co.edu.uniquindio.poo.TipoTorneoGenero;

public class TorneoTest {
        /**
         * Instancia para el manejo de logs
         */
        private static final Logger LOG = Logger.getLogger(TorneoTest.class.getName());

        /**
         * Verificar que la clase Torneo almacene y recupere los datos
         * 
         */

        @Test
        /*
         * Por último, se desea poder obtener un listado de los equipos y el número
         * total de enfrentamientos
         * ganados, empatados y perdidos.
         * Dicho listado debe estar en orden descendente según el número de victorias,
         * empates y perdidas.
         */
        public void ListadoDenfrentamientos() {
                var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300",
                                Genero.MASCULIONO);
                var equipo1 = new Equipo("Uniquindio", representante, Genero.MASCULIONO);
                var equipo2 = new Equipo("Armenia", representante, Genero.MASCULIONO);
                var equipo3 = new Equipo("colombia", representante, Genero.MASCULIONO);

                Resultado resultado = new Resultado(equipo1, equipo2, 2, 1);

                Resultado resultado2 = new Resultado(equipo1, equipo3, 1, 0);

                EstadoEnfrentamiento estadoEnfrentamiento = new EstadoEnfrentamiento(LocalDate.now(), LocalTime.now());
                Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 1),
                                LocalDate.of(2023, 9, 15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                CaracterTorneo.GRUPAL,
                                TipoTorneoGenero.MASCULINO);
                Juez juez = new Juez("JUAN JOSE", "BARRERO", "juanbarrero2277@gmail.com", "3013494998",
                                Genero.MASCULIONO,
                                "12233334");
                LugarEnfrentamiento lugarEnfrentamiento = new LugarEnfrentamiento("UNIVERSIDAD DEL QUINDIO",
                                "ARMENIA-QUINDIO");
                Enfrentamientos enfrentamiento = new Enfrentamientos(LocalDate.now(), LocalTime.now(),
                                lugarEnfrentamiento, equipo1, equipo2, juez, resultado, estadoEnfrentamiento);
                Enfrentamientos enfrentamiento2 = new Enfrentamientos(LocalDate.now(), LocalTime.now(),
                                lugarEnfrentamiento, equipo1, equipo3, juez, resultado2, estadoEnfrentamiento);

                Enfrentamientos enfrentamiento3 = new Enfrentamientos(LocalDate.now(), LocalTime.now(),
                                lugarEnfrentamiento, equipo1, equipo3, juez, resultado, estadoEnfrentamiento);
                torneo.registrarEnfrentamientos(enfrentamiento);
                torneo.registrarEnfrentamientos(enfrentamiento2);
                torneo.registrarEnfrentamientos(enfrentamiento3);

                System.out.println("                                  ");
                System.out.println(torneo.RegistroDeJuegosPorEquipo(equipo1).get(0));
                System.out.println("                                  ");

                System.out.println(torneo.RegistroDeJuegosPorEquipo(equipo1).get(1));
                System.out.println("                                  ");
                System.out.println(torneo.RegistroDeJuegosPorEquipo(equipo1).get(2));
                System.out.println("                                  ");
        }

        @Test
        /* registro de los juez */
        public void registroJuez() {
                LOG.info("Inicio de prueba registro juez ");
                Juez juez = new Juez("JUAN JOSE", "BARRERO", "juanbarrero2277@gmail.com", "3013494998",
                                Genero.MASCULIONO,
                                "12233334");
                Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2024, 10, 1), LocalDate.of(2022, 8, 1),
                                LocalDate.of(2024, 9, 15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                CaracterTorneo.GRUPAL,
                                TipoTorneoGenero.MASCULINO);
                torneo.registroJueces(juez);
                assertEquals(1, torneo.getJuez().size());

        }/*
          * Así mismo es importante brindar a los jueces información relacionada
          * con los enfrentamientos de los cuales hará parte, por lo que se debe poder
          * obtener un listado de los enfrentamientos en que participará un juez basado
          * en su número de licencia.
          */

        @Test
        public void ListadoEnfrentamientosPorJuez() {
                var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300",
                                Genero.MASCULIONO);
                var equipo1 = new Equipo("Uniquindio", representante, Genero.MASCULIONO);
                var equipo2 = new Equipo("Armenia", representante, Genero.MASCULIONO);
                var equipo3 = new Equipo("Armenia", representante, Genero.MASCULIONO);
                Resultado resultado = new Resultado(equipo1, equipo2, 1, 2);
                EstadoEnfrentamiento estadoEnfrentamiento = new EstadoEnfrentamiento(LocalDate.now(), LocalTime.now());
                Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 1),
                                LocalDate.of(2023, 9, 15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                CaracterTorneo.GRUPAL,
                                TipoTorneoGenero.MASCULINO);
                Juez juez = new Juez("JUAN JOSE", "BARRERO", "juanbarrero2277@gmail.com", "3013494998",
                                Genero.MASCULIONO,
                                "12233334");
                LugarEnfrentamiento lugarEnfrentamiento = new LugarEnfrentamiento("UNIVERSIDAD DEL QUINDIO",
                                "ARMENIA-QUINDIO");
                Enfrentamientos enfrentamiento = new Enfrentamientos(LocalDate.now(), LocalTime.now(),
                                lugarEnfrentamiento, equipo1, equipo2, juez, resultado, estadoEnfrentamiento);
                Enfrentamientos enfrentamiento2 = new Enfrentamientos(LocalDate.now(), LocalTime.now(),
                                lugarEnfrentamiento, equipo1, equipo3, juez, resultado, estadoEnfrentamiento);

                torneo.registrarEnfrentamientos(enfrentamiento);
                torneo.registrarEnfrentamientos(enfrentamiento2);

                System.out.println(torneo.buscarEnfrentamientoPorJuez(juez).size());
                assertEquals(2, torneo.buscarEnfrentamientoPorJuez(juez).size());

        }

        /*
         * Se desea brindar información de sus enfrentamientos a cada equipo, por lo
         * que se espera poder obtener un listado de los enfrentamiento de un equipos
         * dado su nombre.
         */

        @Test
        public void ListadoEnfrentamientos() {
                var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300",
                                Genero.MASCULIONO);
                var equipo1 = new Equipo("Uniquindio", representante, Genero.MASCULIONO);
                var equipo2 = new Equipo("Armenia", representante, Genero.MASCULIONO);
                var equipo3 = new Equipo("Armenia", representante, Genero.MASCULIONO);
                Resultado resultado = new Resultado(equipo1, equipo2, 1, 2);
                EstadoEnfrentamiento estadoEnfrentamiento = new EstadoEnfrentamiento(LocalDate.now(), LocalTime.now());
                Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 1),
                                LocalDate.of(2023, 9, 15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                CaracterTorneo.GRUPAL,
                                TipoTorneoGenero.MASCULINO);
                Juez juez = new Juez("JUAN JOSE", "BARRERO", "juanbarrero2277@gmail.com", "3013494998",
                                Genero.MASCULIONO,
                                "12233334");
                LugarEnfrentamiento lugarEnfrentamiento = new LugarEnfrentamiento("UNIVERSIDAD DEL QUINDIO",
                                "ARMENIA-QUINDIO");
                Enfrentamientos enfrentamiento = new Enfrentamientos(LocalDate.now(), LocalTime.now(),
                                lugarEnfrentamiento, equipo1, equipo2, juez, resultado, estadoEnfrentamiento);
                Enfrentamientos enfrentamiento2 = new Enfrentamientos(LocalDate.now(), LocalTime.now(),
                                lugarEnfrentamiento, equipo1, equipo3, juez, resultado, estadoEnfrentamiento);

                torneo.registrarEnfrentamientos(enfrentamiento);
                torneo.registrarEnfrentamientos(enfrentamiento2);
                assertEquals(2, torneo.getEnfrentamientos().size());
                assertEquals(2, torneo.buscarEnfrentamientosPorEquipo(equipo1).size());

        }

        /*
         * Se desea poder agendar los enfrentamientos, indicando el lugar donde
         * se realizará el enfrentamiento (nombre, ubicación), la fecha y hora
         * del enfrentamiento, los equipos que se enfrentarán, el juez o jueces
         * que arbitraran el encuentro, el resultado del enfrentamiento cuando haya
         * concluido (puntos realizados por cada equipo) y el estado del enfrentamiento.
         */
        @Test
        void estadoDeEnfrentamientos() {
                LOG.info("Inicio de prueba datos completos enfrentamientos");
                var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300",
                                Genero.MASCULIONO);
                var equipo1 = new Equipo("Uniquindio", representante, Genero.MASCULIONO);
                var equipo2 = new Equipo("Armenia", representante, Genero.MASCULIONO);
                Resultado resultado = new Resultado(equipo1, equipo2, 1, 2);
                EstadoEnfrentamiento estadoEnfrentamiento = new EstadoEnfrentamiento(LocalDate.now(),
                                LocalTime.now());
                LocalDate fecha = LocalDate.of(2025, 12, 10);
                LocalTime hora = LocalTime.of(21, 10);
                EstadoEnfrentamiento estadoEnfrentamiento1 = new EstadoEnfrentamiento(fecha,
                                hora);

                System.out.println(estadoEnfrentamiento1.EstadoPENDIENTE(fecha, hora));
                EstadoEnfrentamiento estadoEnfrentamiento2 = new EstadoEnfrentamiento(resultado);
                System.out.println(estadoEnfrentamiento1.getEstado());
                assertEquals("PENDIENTE", estadoEnfrentamiento1.getEstado());
                assertEquals("EN JUEGO", estadoEnfrentamiento.getEstado());
                assertEquals("FINALIZADO", estadoEnfrentamiento2.getEstado());

        }

        @Test
        public void datosCompletosEnfrentamientos() {
                LOG.info("Inicio de prueba datos completos enfrentamientos  ");
                LugarEnfrentamiento lugarEnfrentamiento = new LugarEnfrentamiento("UNIVERSIDAD DEL QUINDIO",
                                "ARMENIA-QUINDIO");
                var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300",
                                Genero.MASCULIONO);
                var equipo1 = new Equipo("Uniquindio", representante, Genero.MASCULIONO);
                var equipo2 = new Equipo("Armenia", representante, Genero.MASCULIONO);
                Juez juez = new Juez("JUAN JOSE", "BARRERO", "juanbarrero2277@gmail.com", "3013494998",
                                Genero.MASCULIONO,
                                "12233334");
                Resultado resultado = new Resultado(equipo1, equipo2, 1, 2);

                EstadoEnfrentamiento estadoEnfrentamiento = new EstadoEnfrentamiento(LocalDate.now(),
                                LocalTime.now());
                EstadoEnfrentamiento estadoEnfrentamiento2 = new EstadoEnfrentamiento(resultado);
                Enfrentamientos enfrentamiento = new Enfrentamientos(LocalDate.now(), LocalTime.now(),
                                lugarEnfrentamiento, equipo1, equipo2, juez, resultado, estadoEnfrentamiento);

                assertEquals(LocalTime.now(), enfrentamiento.getHora());
                assertEquals(LocalDate.now(), enfrentamiento.getFecha());
                assertEquals(3, enfrentamiento.getPuntajeequipo2());
                assertEquals(equipo2, resultado.EquipoGanador());
                assertEquals("Uniquindio", enfrentamiento.getEquipo1().getNombreCompleto());
                assertEquals("Armenia", enfrentamiento.getEquipo2().getNombreCompleto());
                assertEquals("UNIVERSIDAD DEL QUINDIO", lugarEnfrentamiento.getNombreLugra());
                assertEquals("ARMENIA-QUINDIO", lugarEnfrentamiento.getUbicacion());

        }

        @Test
        public void datosCompletos() {
                LugarEnfrentamiento lugarEnfrentamiento = new LugarEnfrentamiento("UNIVERSIDAD DEL QUINDIO",
                                "ARMENIA-QUINDIO");
                var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300",
                                Genero.MASCULIONO);
                var equipo1 = new Equipo("Uniquindio", representante, Genero.MASCULIONO);
                var equipo2 = new Equipo("Uniquindio", representante, Genero.MASCULIONO);
                Juez juez = new Juez("JUAN JOSE", "BARRERO", "juanbarrero2277@gmail.com", "3013494998",
                                Genero.MASCULIONO,
                                "12233334");
                Resultado resultado = new Resultado(equipo1, equipo2, 1, 2);

                EstadoEnfrentamiento estadoEnfrentamiento = new EstadoEnfrentamiento(LocalDate.now(),
                                LocalTime.now());
                EstadoEnfrentamiento estadoEnfrentamiento2 = new EstadoEnfrentamiento(resultado);

                System.out.println(estadoEnfrentamiento.EstadoPENDIENTE(LocalDate.of(2024, 12, 9),
                                LocalTime.of(21, 0)));

                Enfrentamientos enfrentamiento = new Enfrentamientos(LocalDate.now(), LocalTime.now(),
                                lugarEnfrentamiento, equipo1, equipo2, juez, resultado, estadoEnfrentamiento);
                System.out.println(estadoEnfrentamiento2.getEstado());
                LOG.info("Inicio de prueba datos completos...");
                // Almacenar los datos de prueba Copa
                // Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|0|LOCAL|GRUPAL
                Torneo torneo = new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 1),
                                LocalDate.of(2023, 9, 15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                CaracterTorneo.GRUPAL,
                                TipoTorneoGenero.MASCULINO);

                // Recuperación y verificación de datos
                assertEquals("Copa Mundo", torneo.getNombre());
                assertEquals(LocalDate.of(2023, 10, 1), torneo.getFechaInicio());
                assertEquals(LocalDate.of(2023, 8, 1), torneo.getFechaInicioInscripciones());
                assertEquals(LocalDate.of(2023, 9, 15), torneo.getFechaCierreInscripciones());
                assertEquals((byte) 24, torneo.getNumeroParticipantes());
                assertEquals((byte) 0, torneo.getLimiteEdad());
                assertEquals(0, torneo.getValorInscripcion());
                assertEquals(TipoTorneo.LOCAL, torneo.getTipoTorneo());
                LOG.info("Fin de prueba datos completos...");
        }

        /**
         * Verificar que la clase Torneo valide que se ingrese los datos
         * 
         */
        @Test
        public void datosNulos() {
                LOG.info("Inicio de prueba datos nulos...");
                // Almacenar los datos de prueba null|null|null|null|24|0|0|null|LOCAL|GRUPAL
                assertThrows(Throwable.class,
                                () -> new Torneo(null, null, null, null, (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                                CaracterTorneo.GRUPAL, TipoTorneoGenero.MASCULINO));

                LOG.info("Fin de prueba datos nulos...");
        }

        /**
         * Verificar que la clase Torneo valide que el ingreso de número de
         * participantes negativo
         * 
         */
        @Test
        public void participantesNegativos() {
                LOG.info("Inicio de prueba número de participantes negativo...");
                // Almacenar los datos de prueba Copa
                // Mundo|2023-10-01|2023-08-01|2023-09-15|-24|0|0|LOCAL|GRUPAL
                assertThrows(Throwable.class,
                                () -> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01),
                                                LocalDate.of(2023, 10, 15), (byte) -24, (byte) 0, 0, TipoTorneo.LOCAL,
                                                CaracterTorneo.GRUPAL,
                                                TipoTorneoGenero.MASCULINO));

                LOG.info("Fin de prueba  número de participantes negativo...");
        }

        /**
         * Verificar que la clase Torneo valide que el ingreso de limites de edades
         * negativo
         * 
         */
        @Test
        public void limiteEdadesNegativo() {
                LOG.info("Inicio de prueba limites de edades negativo...");
                // Almacenar los datos de prueba Copa
                // Mundo|2023-10-01|2023-08-01|2023-09-15|24|-1|0|LOCAL|GRUPAL
                assertThrows(Throwable.class,
                                () -> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01),
                                                LocalDate.of(2023, 10, 15), (byte) 24, (byte) -1, 0, TipoTorneo.LOCAL,
                                                CaracterTorneo.GRUPAL,
                                                TipoTorneoGenero.MASCULINO));

                LOG.info("Fin de prueba  limites de edades negativo...");
        }

        /**
         * Verificar que la clase Torneo valide que el ingreso de valor de inscripción
         * negativa
         * 
         */
        @Test
        public void inscripcionNegativa() {
                LOG.info("Inicio de prueba inscripción negativa...");
                // Almacenar los datos de prueba Copa
                // Mundo|2023-10-01|2023-08-01|2023-09-15|24|0|-1|LOCAL|GRUPAL
                assertThrows(Throwable.class,
                                () -> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 01),
                                                LocalDate.of(2023, 10, 15), (byte) 24, (byte) 0, -1, TipoTorneo.LOCAL,
                                                CaracterTorneo.GRUPAL,
                                                TipoTorneoGenero.MASCULINO));

                LOG.info("Fin de prueba inscripción negativa...");
        }

        /**
         * Verificar que la clase Torneo valide que el ingreso de inscripciones
         * posteriores a la
         * fecha de inicio del torneo
         * 
         */
        @Test
        public void inscripcionTardia() {
                LOG.info("Inicio de prueba inscripción tardia...");
                // Almacenar los datos de prueba Copa
                // Mundo|2023-10-01|2023-11-01|2023-11-15|24|0|0|LOCAL
                assertThrows(Throwable.class,
                                () -> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 11, 01),
                                                LocalDate.of(2023, 11, 15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                                CaracterTorneo.GRUPAL,
                                                TipoTorneoGenero.MASCULINO));

                LOG.info("Fin de prueba inscripción tardia...");
        }

        /**
         * Verificar que la clase Torneo valide que el ingreso de inicio inscripciones
         * posteriores a
         * la fecha de cierre de inscripciones
         * 
         */
        @Test
        public void cierreInscripcionAnteriorInicio() {
                LOG.info("Inicio de prueba Cierre inscripción anterior al inicio...");
                // Almacenar los datos de prueba Copa
                // Mundo|2023-10-01|2023-11-01|2023-11-15|24|0|0|LOCAL|GRUPAL
                assertThrows(Throwable.class,
                                () -> new Torneo("Copa Mundo", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 8, 15),
                                                LocalDate.of(2023, 8, 1), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL,
                                                CaracterTorneo.GRUPAL,
                                                TipoTorneoGenero.MASCULINO));

                LOG.info("Fin de prueba Cierre inscripción anterior al inicio...");
        }
}
