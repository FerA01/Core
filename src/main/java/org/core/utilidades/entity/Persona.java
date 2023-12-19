package org.core.utilidades.entity;
import jakarta.persistence.*;
import org.core.utilidades.util.Util;
import java.util.Date;
@Entity
@Table(name = "persona")
@NamedQueries({
        @NamedQuery(name = "Persona.findById", query = "SELECT p FROM Persona p WHERE p.id =:id"),
        @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
        @NamedQuery(name = "Persona.findByCuit", query = "SELECT p FROM Persona p WHERE p.cuit =:cuit"),
        @NamedQuery(name = "Persona.findByDni", query = "SELECT p FROM Persona p WHERE p.dni =:dni"),
        @NamedQuery(name = "Persona.findByCuitOrDni", query = "SELECT p FROM Persona p WHERE p.dni =:dni OR p.cuit =:cuit"),
        @NamedQuery(name = "Persona.count", query = "SELECT COUNT(p.id) FROM Persona p"),
})
public class Persona extends AbstractEntity {
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni")
    private Long dni;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "cuit")
    private Long cuit;

    public Persona(){}

    @Override
    public String toString(){
        return "Nombre y Apellido: " +  getNombre() + " " + getApellido() + ", DNI: " + getDni() + ", Fecha de nacimiento: " + Util.getFechaFormato(getFechaNacimiento(), "dd/MM/yyyy") + ", Cuit: " + getCuit();
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public Long getDni() {
        return dni;
    }
    public void setDni(Long dni) {
        this.dni = dni;
    }
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public Long getCuit() { return cuit; }
    public void setCuit(Long cuit) { this.cuit = cuit; }
}
