package co.edu.uniquindio.poo;

public class LugarEnfrentamiento {
    private String nombreLugra;
    private String ubicacion;

    public LugarEnfrentamiento(String nombreLugra, String ubicacion) {
        this.nombreLugra = nombreLugra;
        this.ubicacion = ubicacion;
    }

    public String getNombreLugra() {
        return nombreLugra;
    }

    public String getUbicacion() {
        return ubicacion;
    }

}
