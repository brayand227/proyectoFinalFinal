/**
 * Registro que agrupa los datos de un Equipo
 * @author Área de programación UQ
 * @since 2023-09
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Predicate;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public record Equipo(String nombre, Persona representante, Collection<Jugador> jugadores, Genero genero)
        implements Participante {
    public Equipo {
        ASSERTION.assertion(nombre != null && !nombre.isBlank(), "El nombre es requerido");
        ASSERTION.assertion(representante != null, "El representante es requerido");

    }

    public Equipo(String nombre, Persona representante, Genero genero) {
        this(nombre, representante, new LinkedList<>(), genero);
    }

    public Collection<Jugador> getJugadores() {
        return jugadores;
    }

    /*
     * prueba que todos los participantes son de genero masculino
     * 
     */
    public Genero GeneroMasculino() {
        Genero centinela = Genero.MASCULIONO;
        if (genero.equals(Genero.MASCULIONO)) {
            for (Jugador i : jugadores) {
                if (i.getGenero().equals(Genero.FEMENINO)) {
                    centinela = Genero.FEMENINO;
                    break;
                }
            }

        }

        return centinela;
    }

    /* esta funcion nos permite ver si los jugadores son de genero femenino */
    public Genero GeneroFemenino() {
        Genero centinela = Genero.FEMENINO;
        if (genero.equals(Genero.FEMENINO)) {
            for (Jugador i : jugadores) {
                if (i.getGenero().equals(Genero.MASCULIONO)) {
                    centinela = Genero.MASCULIONO;
                    break;
                }
            }

        }

        return centinela;
    }

    /*
     * ESTA FUNCION NOS PERMITE VER SI EL EGIPO ES MIXTO
     */
    public boolean torneoMixto() {
        boolean centinela = false;
        if (GeneroFemenino() == GeneroMasculino() && GeneroMasculino() == GeneroFemenino()) {
            centinela = true;
        }

        return centinela;
    }

    /**
     * Permite registrar un jugador en un equipo siempre y cuando no exista ya un
     * jugador registrado en el equipo con el mismo nombre y apellido
     * 
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(Jugador jugador) {
        validarJugadorExiste(jugador);
        jugadores.add(jugador);
    }

    public Collection<Jugador> jugadores() {
        return jugadores;
    }

    /**
     * Permimte buscar un jugador en el equipo basado en su nombre y apellido.
     * 
     * @param jugador Jugador que se desea buscar
     * @return Optional con el jugador que coincida con el nombre y apellido del
     *         jugador buscado,
     *         o Optinal vacío en caso de no encontrar un jugador en el equipo con
     *         dicho nombre y apellido.
     */
    public Optional<Jugador> buscarJugador(Jugador jugador) {
        Predicate<Jugador> nombreIgual = j -> j.getNombre().equals(jugador.getNombre());
        Predicate<Jugador> apellidoIgual = j -> j.getApellido().equals(jugador.getApellido());
        return jugadores.stream().filter(nombreIgual.and(apellidoIgual)).findAny();
    }

    /**
     * Valida que no exista ya un jugador registrado con el mismo nombre y apellido,
     * en caso de haberlo genera un assertion error.
     */
    private void validarJugadorExiste(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        ASSERTION.assertion(!existeJugador, "El jugador ya esta registrado");
    }

    @Override
    public String getNombreCompleto() {
        return nombre;
    }

    @Override
    public Genero getGenero() {
        return genero;
    }

}
