package entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "departamentos")
public class Departamento {
    @Id
    @Column(name = "dept_no", nullable = false)
    private Byte id;

    @Column(name = "dnombre", length = 15)
    private String dnombre;

    @Column(name = "loc", length = 15)
    private String loc;

    @OneToMany(mappedBy = "deptNo")
    private Set<entities.Empleado> empleados = new LinkedHashSet<>();

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Set<entities.Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<entities.Empleado> empleados) {
        this.empleados = empleados;
    }

}