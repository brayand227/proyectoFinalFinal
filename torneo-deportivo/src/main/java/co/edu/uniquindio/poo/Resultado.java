package co.edu.uniquindio.poo;

import java.util.ArrayList;
import java.util.Optional;

import co.edu.uniquindio.poo.torneodeportivo.Equipo;

public class Resultado {
    private Equipo equipo1;
    private Equipo equipo2;
    private int maracor1;
    private int maracor2;

    public Resultado(Equipo equipo1, Equipo equipo2, int maracor1, int maracor2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.maracor1 = maracor1;
        this.maracor2 = maracor2;
    }

    public Equipo EquipoGanador() {
        Equipo equipo = null;
        if (maracor1 > maracor2) {
            equipo = equipo1;

        } else if (maracor2 > maracor1) {
            equipo = equipo2;

        }

        return equipo;
    }

    public Equipo EquipoPerdedor() {
        Equipo equipo = null;
        if (maracor1 > maracor2) {
            equipo = equipo2;

        } else if (maracor2 > maracor1) {
            equipo = equipo1;

        }

        return equipo;
    }

    public ArrayList<Equipo> Empate() {
        ArrayList<Equipo> equipos = new ArrayList<>();
        if (maracor1 == maracor2) {
            equipos.add(equipo1);
            equipos.add(equipo2);

        }

        return equipos;
    }

}
