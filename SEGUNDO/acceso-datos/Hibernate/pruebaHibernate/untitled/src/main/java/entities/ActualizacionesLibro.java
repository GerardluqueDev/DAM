package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "actualizaciones_libro")
public class ActualizacionesLibro {
    @Column(name = "ISBNAnterior", nullable = false, length = 20)
    private String iSBNAnterior;

    @Column(name = "TituloAnterior", nullable = false, length = 65)
    private String tituloAnterior;

    @Column(name = "NumeroEjemplaresAnterior", nullable = false)
    private Byte numeroEjemplaresAnterior;

    @Column(name = "ISBNNuevo", nullable = false, length = 20)
    private String iSBNNuevo;

    @Column(name = "TituloNuevo", nullable = false, length = 65)
    private String tituloNuevo;

    @Column(name = "NumeroEjemplaresNuevo", nullable = false)
    private Byte numeroEjemplaresNuevo;

    @Column(name = "Usuario", nullable = false, length = 15)
    private String usuario;

    @Column(name = "FechaModificacion", nullable = false)
    private Instant fechaModificacion;

    public String getISBNAnterior() {
        return iSBNAnterior;
    }

    public void setISBNAnterior(String iSBNAnterior) {
        this.iSBNAnterior = iSBNAnterior;
    }

    public String getTituloAnterior() {
        return tituloAnterior;
    }

    public void setTituloAnterior(String tituloAnterior) {
        this.tituloAnterior = tituloAnterior;
    }

    public Byte getNumeroEjemplaresAnterior() {
        return numeroEjemplaresAnterior;
    }

    public void setNumeroEjemplaresAnterior(Byte numeroEjemplaresAnterior) {
        this.numeroEjemplaresAnterior = numeroEjemplaresAnterior;
    }

    public String getISBNNuevo() {
        return iSBNNuevo;
    }

    public void setISBNNuevo(String iSBNNuevo) {
        this.iSBNNuevo = iSBNNuevo;
    }

    public String getTituloNuevo() {
        return tituloNuevo;
    }

    public void setTituloNuevo(String tituloNuevo) {
        this.tituloNuevo = tituloNuevo;
    }

    public Byte getNumeroEjemplaresNuevo() {
        return numeroEjemplaresNuevo;
    }

    public void setNumeroEjemplaresNuevo(Byte numeroEjemplaresNuevo) {
        this.numeroEjemplaresNuevo = numeroEjemplaresNuevo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Instant getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Instant fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

}