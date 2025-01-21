package entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLibro", nullable = false)
    private Integer id;

    @Column(name = "ISBN", nullable = false, length = 20)
    private String isbn;

    @Column(name = "Titulo", nullable = false, length = 65)
    private String titulo;

    @Column(name = "NumeroEjemplares", nullable = false)
    private Byte numeroEjemplares;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idAutor", nullable = false)
    private Autor idAutor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idEditorial", nullable = false)
    private Editorial idEditorial;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idTema", nullable = false)
    private entities.Tema idTema;

    @OneToMany(mappedBy = "idLibro")
    private Set<entities.Prestamo> prestamos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Byte getNumeroEjemplares() {
        return numeroEjemplares;
    }

    public void setNumeroEjemplares(Byte numeroEjemplares) {
        this.numeroEjemplares = numeroEjemplares;
    }

    public Autor getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Autor idAutor) {
        this.idAutor = idAutor;
    }

    public Editorial getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(Editorial idEditorial) {
        this.idEditorial = idEditorial;
    }

    public entities.Tema getIdTema() {
        return idTema;
    }

    public void setIdTema(entities.Tema idTema) {
        this.idTema = idTema;
    }

    public Set<entities.Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Set<entities.Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

}