package org.core.utilidades.entity;
import jakarta.persistence.*;
import org.core.utilidades.util.Util;
import java.util.Date;
@Entity
@Table(name = "organizacion")
@NamedQueries({
        @NamedQuery(name = "Organizacion.findById", query = "SELECT o FROM Organizacion o WHERE o.id =:id"),
        @NamedQuery(name = "Organizacion.findAll", query = "SELECT o FROM Organizacion o"),
        @NamedQuery(name = "Organizacion.findByCuit", query = "SELECT o FROM Organizacion o WHERE o.cuit =:cuit"),
        @NamedQuery(name = "Organizacion.findByRazonSocial", query = "SELECT o FROM Organizacion o WHERE o.razonSocial =:razonSocial"),
})
public class Organizacion extends AbstractEntity {
    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "cuit")
    private Long cuit;

    public Organizacion(){}

    @Override
    public String toString(){
        return "Razón Social: " + getRazonSocial() + ", Fecha de creación: " + Util.getFechaFormato(getFechaCreacion(), "dd/MM/yyyy") + ", Cuit: " + getCuit();
    }


    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Long getCuit() {
        return cuit;
    }
    public void setCuit(Long cuit) {
        this.cuit = cuit;
    }
}
