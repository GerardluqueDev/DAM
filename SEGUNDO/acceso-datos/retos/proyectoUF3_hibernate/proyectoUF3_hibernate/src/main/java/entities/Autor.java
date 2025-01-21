package entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAutor", nullable = false)
    private Integer id;

    @Column(name = "NombreAutor", nullable = false, length = 60)
    private String nombreAutor;

    @OneToMany(mappedBy = "idAutor")
    private Set<entities.Libro> libros = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public Set<entities.Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<entities.Libro> libros) {
        this.libros = libros;
    }

}