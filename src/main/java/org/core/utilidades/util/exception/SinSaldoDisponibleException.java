package org.core.utilidades.util.exception;
import java.math.BigDecimal;
public class SinSaldoDisponibleException extends Exception{
    public static final String MENSAJE_ERROR = "No tienes suficiente saldo para realizar la operación.";
    public static final String MENSAJE_ERROR_MONTO = "No tienes suficiente saldo para realizar la operación por el monto de $";
    public SinSaldoDisponibleException(){ super(MENSAJE_ERROR); }
    public SinSaldoDisponibleException(BigDecimal monto){
        super(MENSAJE_ERROR_MONTO + monto.doubleValue());
    }
}
