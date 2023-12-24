package org.core.utilidades.entity.cuentabancaria;
import jakarta.persistence.*;
import org.core.utilidades.entity.AbstractEntity;
import java.util.Objects;

@Entity
@Table(name = "tipo_transaccion")
@NamedQueries({
        @NamedQuery(name = "TipoTransaccion.findById", query = "SELECT tp FROM TipoTransaccion tp WHERE tp.id =:id"),
        @NamedQuery(name = "TipoTransaccion.findByNombre", query = "SELECT tp FROM TipoTransaccion tp WHERE tp.nombre =:nombre"),
        @NamedQuery(name = "TipoTransaccion.findAll", query = "SELECT tp FROM TipoTransaccion tp"),
})
public class TipoTransaccion extends AbstractEntity {
    @Column(name = "nombre", unique = true)
    private String nombre;

    public TipoTransaccion(){}

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoTransaccion otro = (TipoTransaccion) o;

        return Objects.equals(this.getId(), otro.getId());
    }

    @Override
    public String toString(){ return "Tipo de transaccion: { id= " + getId() + " nombre= " + getNombre() + " }"; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
