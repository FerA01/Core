package org.core.utilidades.entity.cuentabancaria;
import java.math.BigDecimal;
import static org.core.utilidades.business.CuentaBancariaBusiness.LIMITE_CUENTA_SUELDO;

public class CuentaSueldo extends CuentaBancaria{
    @Override
    public boolean permiteSaldoEnDescubierto() { return false; }

    @Override
    public BigDecimal limiteDescubierto() { return LIMITE_CUENTA_SUELDO; }
}
