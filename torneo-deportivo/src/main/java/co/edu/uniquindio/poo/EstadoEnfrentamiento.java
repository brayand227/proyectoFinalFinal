package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.xml.crypto.Data;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

/*
 *  
PENDIENTE: Estado inicial de un enfrentamiento, será posible asignarlo a los enfrentamientos
 que se registran pero que según su fecha y hora programada aun no se han jugado.
EN JUEGO: Estado que indica que el enfrentamiento ya inició. Solo es posible asignar
 este estado si según la fecha y hora de inicio del enfrentamiento es oportuno hacerlo.
FINALIZADO: Estado que indica que el enfrentamiento ha concluido, el sistema debe cambiar 
de forma automática a dicho estado cuando se registra el resultado del enfrentamiento. 
Sin dicho resultado no es posible asignar éste estado.
APLAZADO: Este estado indica que por alguna razón el enfrentamiento no se puede jugar.

 */
public class EstadoEnfrentamiento {
    private String estado;

    public EstadoEnfrentamiento(LocalDate fechaEnfrentamiento, LocalTime horaEnfrentamiento) {

        if (enJuego(fechaEnfrentamiento, horaEnfrentamiento)) {

            setEstado("EN JUEGO");
        } else if (EstadoPENDIENTE(fechaEnfrentamiento, horaEnfrentamiento)) {

            setEstado("PENDIENTE");
        } else
            setEstado("Aplazado");
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EstadoEnfrentamiento(Resultado resultado) {

        FINALIZADO(resultado);

    }

    public boolean EstadoPENDIENTE(LocalDate fechaEnfrentamiento, LocalTime horaEnfrentamiento) {
        boolean centinela = false;
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();
        ASSERTION.assertion(fechaEnfrentamiento != null, "La fecha del enfrentamiento es requerida");
        ASSERTION.assertion(horaEnfrentamiento != null, "La hora del enfrentamiento es requerida");
        if (fechaEnfrentamiento.isAfter(fechaActual) && horaEnfrentamiento.isAfter(horaActual)
                || fechaEnfrentamiento.isAfter(fechaActual) && horaEnfrentamiento.isBefore(horaActual)) {

            centinela = true;
        }
        return centinela;
    }

    public Boolean enJuego(LocalDate fechaEnfrentamiento, LocalTime horaEnfrentamiento) {
        boolean centinela = false;
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();
        ASSERTION.assertion(fechaEnfrentamiento != null, "La fecha del enfrentamiento es requerida");
        ASSERTION.assertion(horaEnfrentamiento != null, "La hora del enfrentamiento es requerida");
        if (fechaEnfrentamiento.isEqual(fechaActual) && horaEnfrentamiento.equals(horaActual)
                || fechaEnfrentamiento.isEqual(fechaActual) && horaEnfrentamiento.isBefore(horaActual)) {

            centinela = true;
        }
        return centinela;
    }

    public void FINALIZADO(Resultado resultado) {
        setEstado("FINALIZADO");
    }

    public String getEstado() {
        return estado;
    }

}
