package org.core.utilidades.entity.cuentabancaria;
import java.math.BigDecimal;

public interface RestriccionCuenta {
    boolean permiteSaldoEnDescubierto();
    BigDecimal limiteDescubierto();
}
