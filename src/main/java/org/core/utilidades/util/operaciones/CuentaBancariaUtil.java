package org.core.utilidades.util.operaciones;
import org.core.utilidades.entity.cuentabancaria.TipoCuenta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CuentaBancariaUtil {
    public static boolean cuentaHabilitada(TipoCuenta tipoCuenta){
        return switch (tipoCuenta) {
            case CAJA_AHORRO -> true;
            case CUENTA_CORRIENTE -> true;
            case CUENTA_SUELDO -> false;
            case CUENTA_MONEDA_EXTRANJERA -> false;
            case CUENTA_REMUNERADA -> false;
            default -> false;
        };
    }

    public static String descripcionTipoCuentaBancaria(TipoCuenta tipoCuenta){
        return switch (tipoCuenta) {
            case CAJA_AHORRO -> "Caja de Ahorro";
            case CUENTA_CORRIENTE -> "Cuenta Corriente";
            case CUENTA_SUELDO -> "Cuenta Sueldo";
            case CUENTA_MONEDA_EXTRANJERA -> "Cuenta Moneda Extranjera";
            case CUENTA_REMUNERADA -> "Cuenta Remunerada";
            default -> "";
        };
    }

    public static List<TipoCuenta> obtenerTiposDeCuentas(){
        return  Arrays.asList(TipoCuenta.values());
    }

    public static List<String> obtenerDescripcionesTiposCuentas(){
        List<String> descripciones = new ArrayList<>();
        obtenerTiposDeCuentas().forEach(
                tipoCuenta -> {
                    descripciones.add(descripcionTipoCuentaBancaria(tipoCuenta));
                }
        );
        return descripciones;
    }

    public static BigDecimal restar(BigDecimal monto, BigDecimal monto2){ return monto.subtract(monto2); }
    public static BigDecimal sumar(BigDecimal monto, BigDecimal monto2){ return monto.add(monto2); }
    public static BigDecimal multiplicar(BigDecimal monto, Integer valor){ return monto.multiply(BigDecimal.valueOf(valor)); }
    public static boolean saldoMayorIgualA(BigDecimal monto1, BigDecimal monto2){
        int resultado = monto1.compareTo(monto2);
        return resultado >= 0;
    }
}
