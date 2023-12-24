package org.core.utilidades.entity;
import jakarta.persistence.*;
import org.core.utilidades.util.Util;

import java.util.Date;
@Entity
@Table(name = "usuario_organizacion")
@NamedQueries({
        @NamedQuery(name = "UsuarioOrganizacion.findById", query = "SELECT uo FROM UsuarioOrganizacion AS uo WHERE uo.id =:id"),
        @NamedQuery(name = "UsuarioOrganizacion.findByUsuario", query = "SELECT uo FROM UsuarioOrganizacion AS uo WHERE uo.usuario =:usuario"),
        @NamedQuery(name = "UsuarioOrganizacion.findAll", query = "SELECT uo FROM UsuarioOrganizacion AS uo"),
        @NamedQuery(name = "UsuarioOrganizacion.findByCuit", query = "SELECT uo FROM UsuarioOrganizacion AS uo WHERE uo.organizacion.cuit =:cuit"),
        @NamedQuery(name = "UsuarioOrganizacion.findByOrganizacion", query = "SELECT uo FROM UsuarioOrganizacion AS uo WHERE uo.organizacion =:organizacion"),
})
public class UsuarioOrganizacion extends AbstractEntity {
    @Column(name = "usuario")
    private String usuario;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @Column(name = "logueado")
    private boolean logueado;

    @OneToOne
    @JoinColumn(name = "organizacion_id", unique = true)
    private Organizacion organizacion;

    public UsuarioOrganizacion() {}


    @Override
    public String toString() {
        return "{ Usuario: " + getUsuario() +
                " Fecha de alta " + Util.getFechaFormato(getFechaAlta(), "dd/MM/yyyy") +
                " Logueado " + isLogueado() +
                " \n Organización { " + getOrganizacion().toString() + " }" +
                " \n }";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean isLogueado() {
        return logueado;
    }

    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }
}
