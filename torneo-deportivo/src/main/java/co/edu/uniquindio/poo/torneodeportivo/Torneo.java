/**
 * Clase que agrupa los datos de un Torneo
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import co.edu.uniquindio.poo.Enfrentamientos;
import co.edu.uniquindio.poo.Juez;
import co.edu.uniquindio.poo.TipoTorneoGenero;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public class Torneo {
    private final TipoTorneoGenero tipoTorneoGenero;

    private final String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaInicioInscripciones;
    private LocalDate fechaCierreInscripciones;
    private final byte numeroParticipantes;
    private final byte limiteEdad;
    private final int valorInscripcion;
    private final TipoTorneo tipoTorneo;
    private final Collection<Juez> juezs;
    private final Collection<Participante> participantes;
    private final CaracterTorneo caracter;
    private final Collection<Enfrentamientos> enfrentamientos;

    public Torneo(String nombre, LocalDate fechaInicio,
            LocalDate fechaInicioInscripciones,
            LocalDate fechaCierreInscripciones, byte numeroParticipantes,
            byte limiteEdad, int valorInscripcion, TipoTorneo tipoTorneo, CaracterTorneo caracter,
            TipoTorneoGenero tipoTorneoGenero) {

        ASSERTION.assertion(nombre != null, "El nombre es requerido");
        ASSERTION.assertion(numeroParticipantes >= 0, "El número de participantes no puede ser negativo");
        ASSERTION.assertion(limiteEdad >= 0, "El limite de edad no puede ser negativo");
        ASSERTION.assertion(valorInscripcion >= 0, "El valor de la inscripción no puede ser negativo");

        this.nombre = nombre;

        setFechaInicioInscripciones(fechaInicioInscripciones);
        setFechaCierreInscripciones(fechaCierreInscripciones);
        setFechaInicio(fechaInicio);
        this.numeroParticipantes = numeroParticipantes;
        this.limiteEdad = limiteEdad;
        this.valorInscripcion = valorInscripcion;
        this.tipoTorneo = tipoTorneo;
        this.enfrentamientos = new LinkedList<>();
        this.participantes = new LinkedList<>();
        this.juezs = new LinkedList<>();
        this.caracter = Objects.requireNonNull(caracter, "El carácter del torneo es requerido");
        this.tipoTorneoGenero = tipoTorneoGenero;
    }

    public void registrarEnfrentamientos(Enfrentamientos enfrentamiento) {
        enfrentamientos.add(enfrentamiento);

    }

    public Collection<Juez> getJuez() {
        return juezs;
    }

    public Collection<Enfrentamientos> getEnfrentamientos() {
        return enfrentamientos;
    }

    public TipoTorneoGenero getGenero() {
        return tipoTorneoGenero;
    }

    public int ValidarGeneroMixto() {

        int centinela = 0;

        return centinela;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaInicioInscripciones() {
        return fechaInicioInscripciones;
    }

    public LocalDate getFechaCierreInscripciones() {
        return fechaCierreInscripciones;
    }

    public byte getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public byte getLimiteEdad() {
        return limiteEdad;
    }

    public int getValorInscripcion() {
        return valorInscripcion;
    }

    public TipoTorneo getTipoTorneo() {
        return tipoTorneo;
    }

    public CaracterTorneo getCaracter() {
        return caracter;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        ASSERTION.assertion(fechaInicio != null, "La fecha de inicio es requerida");
        ASSERTION.assertion((fechaInicioInscripciones == null || fechaInicio.isAfter(fechaInicioInscripciones)) &&
                (fechaCierreInscripciones == null || fechaInicio.isAfter(fechaCierreInscripciones)),
                "La fecha de inicio no es válida");
        this.fechaInicio = fechaInicio;
    }

    public void setFechaInicioInscripciones(LocalDate fechaInicioInscripciones) {
        ASSERTION.assertion(fechaInicioInscripciones != null, "La fecha de inicio de inscripciones es requerida");
        this.fechaInicioInscripciones = fechaInicioInscripciones;
    }

    public void setFechaCierreInscripciones(LocalDate fechaCierreInscripciones) {
        ASSERTION.assertion(fechaCierreInscripciones != null, "La fecha de cierre es requerida");
        ASSERTION.assertion(fechaCierreInscripciones.isAfter(fechaInicioInscripciones),
                "La fecha de cierre de inscripciones debe ser posterior a la fecha de inicio de inscripciones");
        this.fechaCierreInscripciones = fechaCierreInscripciones;
    }

    /**
     * Permite registrar un participante en el torneo
     * 
     * @param participante Participante a ser registrado
     * @throws Se genera un error si ya existe un equipo registrado con el mismo
     *            nombre, o en caso de que las inscripciones del torneo no estén
     *            abiertas.
     */
    public void registrarParticipante(Participante participante) {
        validarParticipanteExiste(participante);

        validarInscripciopnesAbiertas();
        validarCaracter(participante);

        participantes.add(participante);
    }

    /* Registrar los jueces del torneo */
    public void registroJueces(Juez juez) {

        validarInscripciopnesAbiertas();

        juezs.add(juez);
    }

    /*
     * Valida que todos las participantes en un torneo masculino sean de genero
     * masculino
     * 
     */
    public boolean verificarGeneroMasculino() {
        boolean centinela = true;
        if (tipoTorneoGenero.equals(tipoTorneoGenero.MASCULINO)) {
            for (Participante i : participantes) {
                if (i.getGenero().equals(Genero.FEMENINO)) {
                    centinela = false;
                    break;

                }

            }
        }

        return centinela;
    }
    /*
     * Valida que todas las participantes en un torneo femenino sean de genero
     * femenino
     * 
     */

    public Optional<Participante> verificarGnereoFemenino() {

        Predicate<Participante> condicion = participante -> participante.getGenero().equals(Genero.MASCULIONO);

        return participantes.stream().filter(condicion).findAny();
    }
    /*
     * en esta funcion buscamos comprobar que almenos cada equipo mixto tenga un
     * jugador de diferente genero
     */

    public boolean TorneoMixto() {
        boolean centinela = false;
        if (verificarGnereoFemenino().isPresent() && verificarGeneroMasculino() == true) {
            centinela = true;
        }
        return centinela;
    }

    /**
     * Valida que el participante sea acorde con el carácter del torneo.
     * 
     * @param participante Participante a ser registrado
     */
    private void validarCaracter(Participante participante) {
        ASSERTION.assertion(caracter.esValido(participante), "Las inscripciones no están abiertas");
    }

    /**
     * Valida que las inscripciones del torneo esten abiertas, en caso de no estarlo
     * genera un assertion error.
     */
    private void validarInscripciopnesAbiertas() {
        boolean inscripcionAbierta = fechaInicioInscripciones.isBefore(LocalDate.now())
                && fechaCierreInscripciones.isAfter(LocalDate.now());
        ASSERTION.assertion(inscripcionAbierta, "Las  inscripciones no están abiertas");
    }

    /**
     * Valida que no exista ya un equipo registrado con el mismo nombre, en caso de
     * haberlo genera un assertion error.
     */
    private void validarParticipanteExiste(Participante participante) {
        boolean existeEquipo = buscarParticipantePorNombre(participante.getNombreCompleto()).isPresent();
        ASSERTION.assertion(!existeEquipo, "El equipo ya esta registrado");
    }

    /**
     * Permite obtener una copia no modificable de la lista de los participantes
     * registrados.
     * 
     * @return Collection<Participante> no modificable de los participantes
     *         registrados en el torneo.
     */
    public Collection<Participante> getParticipantes() {
        return Collections.unmodifiableCollection(participantes);
    }

    /**
     * Permite buscar un participante por su nombre entre los participantes
     * registrados en el torneo
     * 
     * @param nombre Nombre del participante que se está buscando
     * @return Un Optional<Participante> con el participante cuyo nombre sea igual
     *         al nombre buscado, o un Optional vacío en caso de no encontrar un
     *         participante con nombre igual al dado.
     */
    public Optional<Participante> buscarParticipantePorNombre(String nombre) {
        Predicate<Participante> condicion = participante -> participante.getNombreCompleto().equals(nombre);
        return participantes.stream().filter(condicion).findAny();
    }

    /**
     * Permite registrar un jugador en el equipo siempre y cuando este dentro de las
     * fechas validas de registro,
     * no exista ya un jugador registrado con el mismo nombre y apellido y el
     * jugador no exceda el limite de edad del torneo.
     * 
     * @param nombre  Nombre del equipo en que se desea registrar el jugador
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(String nombre, Jugador jugador) {
        var participante = buscarParticipantePorNombre(nombre);

        participante.ifPresent((e) -> {
            if (e instanceof Equipo equipo) {
                registrarJugador(equipo, jugador);
            }
        });
    }

    /**
     * Permite registrar un jugador en el equipo siempre y cuando este dentro de las
     * fechas validas de registro,
     * no exista ya un jugador registrado con el mismo nombre y apellido y el
     * jugador no exceda el limite de edad del torneo.
     * 
     * @param equipo  Equipo en el que se desea registrar el jugador.
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(Equipo equipo, Jugador jugador) {
        ASSERTION.assertion(!LocalDate.now().isAfter(fechaCierreInscripciones),
                "No se pueden registrar jugadores después del a fecha de cierre de inscripciones");
        validarLimiteEdadJugador(jugador);
        validarJugadorExiste(jugador);
        equipo.registrarJugador(jugador);
    }

    /**
     * Permite buscar un jugador basado en su nombre y apellido en todos los equipos
     * registrados en el torneo.
     * 
     * @param jugador Jugador que se desea buscar
     * @return Optional con el jugador encontrado o un optional vacío en caso de no
     *         haber encontrado un jugador con el nombre y apellido del jugador
     *         buscado.
     */
    public Optional<Jugador> buscarJugador(Jugador jugador) {
        return participantes.stream()
                .filter(p -> p instanceof Equipo)
                .map(p -> (Equipo) p)
                .map(equipo -> equipo.buscarJugador(jugador))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny();
    }

    /**
     * Valida que no exista ya un jugador registrado con el mismo nombre y apellido,
     * en caso de haberlo genera un assertion error.
     */
    private void validarJugadorExiste(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        ASSERTION.assertion(!existeJugador, "El jugador ya esta registrado");
    }

    /**
     * Valida que no exista se puedan registrar jugadores que al momento del inicio
     * del torneo excedan el limite de edad.
     */
    private void validarLimiteEdadJugador(Jugador jugador) {
        var edadAlInicioTorneo = jugador.calcularEdad(fechaInicio);
        ASSERTION.assertion(limiteEdad == 0 || limiteEdad >= edadAlInicioTorneo,
                "No se pueden registrar jugadores que excedan el limite de edad del torneo");
    }
    /*
     * Se desea brindar información de sus enfrentamientos a cada equipo,
     * por lo que se espera poder obtener un listado de los enfrentamiento de un
     * equipos dado su nombre.
     */

    public ArrayList<Enfrentamientos> buscarEnfrentamientosPorEquipo(Equipo equipo) {
        ArrayList<Enfrentamientos> enfrentamientos2 = new ArrayList<>();
        for (Enfrentamientos i : enfrentamientos) {
            if (i.getEquipo1().equals(equipo) || i.getEquipo2().equals(equipo)) {
                enfrentamientos2.add(i);
            }

        }

        return enfrentamientos2;
    }

    /*
     * Así mismo es importante brindar a los jueces información relacionada con los
     * enfrentamientos
     * de los cuales hará parte, por lo que
     * se debe poder obtener un listado de los enfrentamientos en que participará un
     * juez basado
     * en su número de licencia.
     */
    public ArrayList<Enfrentamientos> buscarEnfrentamientoPorJuez(Juez juez) {
        ArrayList<Enfrentamientos> enfrentamientos2 = new ArrayList<>();
        for (Enfrentamientos i : enfrentamientos) {
            if (i.getJuez().equals(juez)) {
                enfrentamientos2.add(i);
            }

        }

        return enfrentamientos2;

    }

    /*
     * Por último, se desea poder obtener un listado de los equipos y
     * el número total de enfrentamientos ganados, empatados y perdidos.
     * Dicho listado debe estar en orden descendente según el número de victorias,
     * empates y perdidas.
     */

    public ArrayList<Integer> RegistroDeJuegosPorEquipo(Equipo equipo) {
        ArrayList<Integer> registro = new ArrayList<>();
        int victorias = EquipoGanador(equipo);
        int empates = EquipoEmpate(equipo);
        int Derrotas = EquipoPerdedor(equipo);
        registro.add(0, victorias);
        registro.add(1, empates);
        registro.add(2, Derrotas);
        return registro;

    }

    public int EquipoGanador(Equipo equipo) {
        int victorias = 0;
        for (Enfrentamientos i : enfrentamientos) {
            if (i.getEquipoGanador().equals(equipo)) {
                victorias += 1;
            }
        }

        return victorias;
    }

    public int EquipoEmpate(Equipo equipo) {
        int Empates = 0;
        int total = buscarEnfrentamientosPorEquipo(equipo).size();
        int victorias = EquipoGanador(equipo);
        int Derrotas = EquipoPerdedor(equipo);
        Empates = total - (victorias + Derrotas);
        return Empates;
    }

    public int EquipoPerdedor(Equipo equipo) {
        int Derrotas = 0;
        for (Enfrentamientos i : enfrentamientos) {
            if (i.getEquipoPerdedor().equals(equipo)) {
                Derrotas += 1;
            }
        }
        return Derrotas;
    }

}
