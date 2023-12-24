package org.core.utilidades.entity;
import jakarta.persistence.*;
import org.core.utilidades.util.Util;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "usuario_persona")
@NamedQueries({
        @NamedQuery(name = "UsuarioPersona.findById", query = "SELECT up FROM UsuarioPersona up WHERE up.id =:id"),
        @NamedQuery(name = "UsuarioPersona.findAll", query = "SELECT up FROM UsuarioPersona up"),
        @NamedQuery(name = "UsuarioPersona.findByPersona", query = "SELECT up FROM UsuarioPersona up WHERE up.persona =:persona"),
        @NamedQuery(name = "UsuarioPersona.findByUsuario", query = "SELECT up FROM UsuarioPersona up WHERE up.usuario =:usuario"),

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

    @OneToOne
    @JoinColumn(name = "persona_id", unique = true)
    private Persona persona;

    public UsuarioPersona() {}

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioPersona otro = (UsuarioPersona) o;

        return Objects.equals(this.getId(), otro.getId());
    }

    @Override
    public String toString() {
        return "{ Usuario: " + getUsuario() +
                " Fecha de alta " + Util.getFechaFormato(getFechaAlta(), "dd/MM/yyyy") +
                " Logueado " + isLogueado() +
                " \n Persona { " + getPersona().toString() + " }" +
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
    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }
}