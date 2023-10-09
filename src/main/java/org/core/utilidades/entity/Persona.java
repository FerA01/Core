package org.core.utilidades.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.core.utilidades.util.Util;
import java.util.Date;
import java.util.logging.Level;
@Entity
@Table(name = "persona")
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

    public Persona(){
        getLogger().log(Level.INFO, "Creando entidad " + this.getClass().getName());
    }

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
