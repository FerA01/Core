package org.core.utilidades.entity.cuentabancaria;
import jakarta.persistence.*;
import org.core.utilidades.entity.AbstractEntity;
import org.core.utilidades.util.Util;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "movimiento")
public class Movimiento extends AbstractEntity {
    @Column(name = "monto")
    private BigDecimal monto;
    @Column(name = "fecha")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToOne
    @JoinColumn(name = "tipo")
    private TipoTransaccion tipoTransaccion;

    @ManyToOne
    @JoinColumn(name = "cuenta_origen_id")
    private CuentaBancaria cuentaOrigen;

    @ManyToOne
    @JoinColumn(name = "cuenta_destino_id")
    private CuentaBancaria cuentaDestino;

    public Movimiento(){}


    @Override
    public String toString(){
        return "Movimiento { id= " + getId() + " monto= $" + getMonto() + " fecha= "
                + Util.getFechaFormato(getFecha(), "dd/MM/yyyy HH:mm:ss")
                + " tipo transaccion= " + getTipoTransaccion().getNombre() + " }";
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movimiento otro = (Movimiento) o;

        return Objects.equals(this.getId(), otro.getId());
    }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public TipoTransaccion getTipoTransaccion() { return tipoTransaccion; }
    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) { this.tipoTransaccion = tipoTransaccion; }
    public CuentaBancaria getCuentaOrigen() { return cuentaOrigen; }
    public void setCuentaOrigen(CuentaBancaria cuentaOrigen) { this.cuentaOrigen = cuentaOrigen; }
    public CuentaBancaria getCuentaDestino() { return cuentaDestino; }
    public void setCuentaDestino(CuentaBancaria cuentaDestino) { this.cuentaDestino = cuentaDestino; }
}
