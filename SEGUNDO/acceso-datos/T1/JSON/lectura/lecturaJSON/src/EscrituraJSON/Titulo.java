package EscrituraJSON;

import java.util.ArrayList;

public class Titulo {
    private String nombre;
    private ArrayList<Jugador> jugadores;

    public Titulo(String nombre) {
        this.nombre = nombre;
        jugadores = new ArrayList<Jugador>();
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean add(Jugador jugador) {
        return jugadores.add(jugador);
    }

    @Override
    public String toString() {
        return "EscrituraJSON.Titulo{" +
                "nombre='" + nombre + '\'' +
                ", jugadores=" + jugadores +
                '}';
    }
}
