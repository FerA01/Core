package org.core.utilidades.entity.cuentabancaria;
import jakarta.persistence.*;
import org.core.utilidades.entity.AbstractEntity;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cuenta_bancaria")
public class CuentaBancaria extends AbstractEntity {
    @Column(name = "cbu")
    private BigInteger cbu;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "tipo_cuenta")
    private String tipoCuenta;

    @Column(name = "fecha_apertura")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fechaApertura;

    @OneToMany
    @JoinColumn(referencedColumnName = "movimientos_id")
    private List<Movimiento> movimientos;

    public CuentaBancaria(){}


    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movimiento otro = (Movimiento) o;

        return Objects.equals(this.getId(), otro.getId());
    }

    public BigInteger getCbu() { return cbu; }
    public void setCbu(BigInteger cbu) { this.cbu = cbu; }
    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
    public String getTipoCuenta() { return tipoCuenta; }
    public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }
    public Date getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(Date fechaApertura) { this.fechaApertura = fechaApertura; }
}
