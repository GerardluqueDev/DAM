package entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "editorial")
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEditorial", nullable = false)
    private Integer id;

    @Column(name = "NombreEditorial", nullable = false, length = 30)
    private String nombreEditorial;

    @Column(name = "Direccion", nullable = false, length = 100)
    private String direccion;

    @Column(name = "Telefono", nullable = false, length = 15)
    private String telefono;

    @OneToMany(mappedBy = "idEditorial")
    private Set<entities.Libro> libros = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<entities.Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<entities.Libro> libros) {
        this.libros = libros;
    }

}