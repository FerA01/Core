package org.core.utilidades.entity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.logging.Level;

@Entity
@Table(name = "usuario_persona")
@NamedQueries({
        @NamedQuery(name = "UsuarioPersona.findById", query = "SELECT up FROM UsuarioPersona up WHERE up.id =:id"),

}
)
public class UsuarioPersona extends AbstractEntity {
    @Column(name = "usuario")
    private String usuario;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @Column(name = "logueado")
    private boolean logueado;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private Persona persona;


    public UsuarioPersona() {
        getLogger().log(Level.INFO, "Creando entidad " + getClass().getName());
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
    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }
}