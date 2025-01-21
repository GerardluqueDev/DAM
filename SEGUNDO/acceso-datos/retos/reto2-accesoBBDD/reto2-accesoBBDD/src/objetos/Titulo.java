package objetos;

import java.util.ArrayList;

public class Titulo {
    private String titulo;
    private ArrayList<Heroe> heroes;

    public Titulo(String titulo) {
        this.titulo = titulo;
        heroes = new ArrayList<Heroe>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Heroe> getHeroes() {
        return heroes;
    }

    public void setHeroes(ArrayList<Heroe> heroes) {
        this.heroes = heroes;
    }

    public boolean add(Heroe heroe) {
        return heroes.add(heroe);
    }
}
