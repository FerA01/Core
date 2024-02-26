package org.core.utilidades.entity.cuentabancaria;
import jakarta.persistence.*;
import org.core.utilidades.entity.AbstractEntity;
import org.core.utilidades.util.Util;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cuenta_bancaria")
@NamedQueries({
        @NamedQuery(name = "CuentaBancaria.findById", query = "SELECT cb FROM CuentaBancaria cb WHERE cb.id =:id"),
        @NamedQuery(name = "CuentaBancaria.findByCbu", query = "SELECT cb FROM CuentaBancaria cb WHERE cb.cbu =:cbu"),
        @NamedQuery(name = "CuentaBancaria.findByTipoCuenta", query = "SELECT cb FROM CuentaBancaria cb WHERE cb.tipoCuenta =:tipoCuenta"),
        @NamedQuery(name = "CuentaBancaria.findAll", query = "SELECT cb FROM CuentaBancaria cb"),
})
public class CuentaBancaria extends AbstractEntity implements RestriccionCuenta{
    @Column(name = "cbu")
    private String cbu;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "tipo_cuenta")
    private String tipoCuenta;

    @Column(name = "fecha_apertura")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fechaApertura;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movimientos_id", referencedColumnName = "id")
    private List<Movimiento> movimientos;

    public CuentaBancaria(){ super(); }
    public CuentaBancaria(String cbu, String tipoCuenta){
        setCbu(cbu);
        setTipoCuenta(tipoCuenta);
        setFechaApertura(new Date());
        setSaldo(BigDecimal.valueOf(0));
    }

    @Override
    public boolean permiteSaldoEnDescubierto() {
        return false;
    }

    @Override
    public BigDecimal limiteDescubierto() {
        return BigDecimal.ZERO;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CuentaBancaria otro = (CuentaBancaria) o;

        return Objects.equals(this.getId(), otro.getId());
    }

    @Override
    public String toString(){
        return "Cuenta Bancaria: { id: " + getId() + " Cbu: " + getCbu() + " Fecha de apertura: "
                + Util.getFechaFormato(getFechaApertura(), "dd/MM/yyyy HH:mm:ss") + " Saldo: $" + getSaldo() + " }";
    }

    public String getCbu() { return cbu; }
    public void setCbu(String cbu) { this.cbu = cbu; }
    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
    public String getTipoCuenta() { return tipoCuenta; }
    public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }
    public Date getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(Date fechaApertura) { this.fechaApertura = fechaApertura; }
    public List<Movimiento> getMovimientos() { return movimientos; }
    public void setMovimientos(List<Movimiento> movimientos) { this.movimientos = movimientos; }
}
