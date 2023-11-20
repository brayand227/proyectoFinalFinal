package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.torneodeportivo.Genero;
import co.edu.uniquindio.poo.torneodeportivo.Persona;

/*licencia de juez, el nombre y apellido, email y el número celular. */
public class Juez extends Persona {
    private String licenciaJuez;

    public Juez(String nombre, String apellido, String email, String celular, Genero genero, String licenciaJuez) {
        super(nombre, apellido, email, celular, genero);
        this.licenciaJuez = licenciaJuez;
    }

    public String getLicenciaJuez() {
        return licenciaJuez;
    }
}
