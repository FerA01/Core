package org.core.utilidades.entity.cuentabancaria;
import java.math.BigDecimal;
import static org.core.utilidades.business.CuentaBancariaBusiness.LIMITE_CAJA_AHORRO;

public class CuentaCajaAhorro extends CuentaBancaria{
    @Override
    public boolean permiteSaldoEnDescubierto() { return true; }

    @Override
    public BigDecimal limiteDescubierto() { return LIMITE_CAJA_AHORRO; }
}
