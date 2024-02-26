package org.core.utilidades.util.operaciones;
import org.core.utilidades.entity.cuentabancaria.TipoCuenta;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class CuentaBancariaTest {
    @Test
    public void deberiaDevolverTipoCuentaCajaAhorroTrue(){
        TipoCuenta tipoCuenta = TipoCuenta.CAJA_AHORRO;
        assertTrue(CuentaBancariaUtil.cuentaHabilitada(tipoCuenta));
    }
    @Test
    public void deberiaDevolverTipoCuentaCuentaCorrienteTrue(){
        TipoCuenta tipoCuenta = TipoCuenta.CUENTA_CORRIENTE;
        assertTrue(CuentaBancariaUtil.cuentaHabilitada(tipoCuenta));
    }
    @Test
    public void deberiaDevolverTipoCuentaCuentaSaldoFalse(){
        TipoCuenta tipoCuenta = TipoCuenta.CUENTA_SUELDO;
        assertFalse(CuentaBancariaUtil.cuentaHabilitada(tipoCuenta));
    }
    @Test
    public void deberiaDevolverTipoCuentaCuentaRemuneradaFalse(){
        TipoCuenta tipoCuenta = TipoCuenta.CUENTA_REMUNERADA;
        assertFalse(CuentaBancariaUtil.cuentaHabilitada(tipoCuenta));
    }
    @Test
    public void deberiaDevolverTipoCuentaCuentaMonedaExtranjeraFalse(){
        TipoCuenta tipoCuenta = TipoCuenta.CUENTA_MONEDA_EXTRANJERA;
        assertFalse(CuentaBancariaUtil.cuentaHabilitada(tipoCuenta));
    }

    @Test
    public void deberiaDevolverDescripcionCajaAhorro(){
        TipoCuenta tipoCuenta = TipoCuenta.CAJA_AHORRO;
        assertEquals("Caja de Ahorro", CuentaBancariaUtil.descripcionTipoCuentaBancaria(tipoCuenta));
    }

    @Test
    public void deberiaDevolverDescripcionCuentaCorriente(){
        TipoCuenta tipoCuenta = TipoCuenta.CUENTA_CORRIENTE;
        assertEquals("Cuenta Corriente", CuentaBancariaUtil.descripcionTipoCuentaBancaria(tipoCuenta));
    }

    @Test
    public void deberiaDevolverDescripcionCuentaSueldo(){
        TipoCuenta tipoCuenta = TipoCuenta.CUENTA_SUELDO;
        assertEquals("Cuenta Sueldo", CuentaBancariaUtil.descripcionTipoCuentaBancaria(tipoCuenta));
    }

    @Test
    public void deberiaDevolverDescripcionCuentaRemunerada(){
        TipoCuenta tipoCuenta = TipoCuenta.CUENTA_REMUNERADA;
        assertEquals("Cuenta Remunerada", CuentaBancariaUtil.descripcionTipoCuentaBancaria(tipoCuenta));
    }

    @Test
    public void deberiaDevolverDescripcionCuentaMonedaExtranjera(){
        TipoCuenta tipoCuenta = TipoCuenta.CUENTA_MONEDA_EXTRANJERA;
        assertEquals("Cuenta Moneda Extranjera", CuentaBancariaUtil.descripcionTipoCuentaBancaria(tipoCuenta));
    }

    @Test
    public void deberiaDevolverTodasLasDescripciones(){
        List<String> descripciones = CuentaBancariaUtil.obtenerDescripcionesTiposCuentas();
        assertNotNull(descripciones);
        assertEquals(5, descripciones.size());
    }

    @Test
    public void deberiaSumarBigDecimal(){
        BigDecimal valor1 = BigDecimal.ONE;
        BigDecimal valor2 = BigDecimal.valueOf(3L);

        assertEquals(BigDecimal.valueOf(4L), CuentaBancariaUtil.sumar(valor1, valor2));
    }
    @Test
    public void deberiaRestarBigDecimal(){
        BigDecimal valor1 = BigDecimal.valueOf(10L);
        BigDecimal valor2 = BigDecimal.valueOf(8L);

        assertEquals(BigDecimal.valueOf(2L), CuentaBancariaUtil.restar(valor1, valor2));
    }

    @Test
    public void deberiaMultiplicarBigDecimal(){
        BigDecimal valor1 = BigDecimal.valueOf(10L);
        Integer valor2 = -2;

        assertEquals(BigDecimal.valueOf(-20L), CuentaBancariaUtil.multiplicar(valor1, valor2));
    }

    @Test
    public void deberiaCompararSiMontoAEsMayorAMontoBResultadoFalso(){
        BigDecimal monto1 = BigDecimal.valueOf(50L);
        BigDecimal monto2 = BigDecimal.valueOf(100);

        assertFalse(CuentaBancariaUtil.saldoMayorIgualA(monto1, monto2));
    }

    @Test
    public void deberiaCompararSiMontoAEsMayorAMontoBResultadoVerdadero(){
        BigDecimal monto1 = BigDecimal.valueOf(150L);
        BigDecimal monto2 = BigDecimal.valueOf(80);

        assertTrue(CuentaBancariaUtil.saldoMayorIgualA(monto1, monto2));
    }
    @Test
    public void deberiaCompararSiMontoAEsMayorAMontoBResultadoVerdadero2(){
        BigDecimal monto1 = BigDecimal.valueOf(250000L);
        BigDecimal monto2 = BigDecimal.valueOf(250000L);

        assertTrue(CuentaBancariaUtil.saldoMayorIgualA(monto1, monto2));
    }
}