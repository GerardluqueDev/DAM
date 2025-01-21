package escrituraJSON;

import java.util.ArrayList;

public class Titulo {
    String titulo;
    ArrayList<Libro> libros;

    public Titulo(String titulo) {
        this.titulo = titulo;
        libros = new ArrayList<Libro>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }
    public void addLibro(Libro libro) {
        libros.add(libro);
    }
}
