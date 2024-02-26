package org.core.utilidades.entity.cuentabancaria;
import java.math.BigDecimal;
import static org.core.utilidades.business.CuentaBancariaBusiness.LIMITE_CUENTA_CORRIENTE;

public class CuentaCorriente extends CuentaBancaria {
    @Override
    public boolean permiteSaldoEnDescubierto() { return true; }

    @Override
    public BigDecimal limiteDescubierto() { return LIMITE_CUENTA_CORRIENTE; }
}
