package org.core.utilidades.entity;
import jakarta.persistence.*;
import org.core.utilidades.util.Util;
import java.util.Date;
import java.util.logging.Level;
@Entity
@Table(name = "organizacion")
public class Organizacion extends AbstractEntity {
    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "cuit")
    private Long cuit;

    public Organizacion(){
        getLogger().log(Level.INFO, "Creando entidad " + this.getClass().getName());
    }

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
