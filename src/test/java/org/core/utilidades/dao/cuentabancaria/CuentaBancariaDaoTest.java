package org.core.utilidades.dao.cuentabancaria;
import org.core.utilidades.business.CuentaBancariaBusiness;
import org.core.utilidades.dependencia.cuentabancaria.CuentaBancariaDependencia;
import org.core.utilidades.entity.cuentabancaria.CuentaBancaria;
import org.core.utilidades.entity.cuentabancaria.TipoCuenta;
import org.core.utilidades.util.operaciones.CuentaBancariaUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import static org.junit.jupiter.api.Assertions.*;
class CuentaBancariaDaoTest {
    private CuentaBancariaDao dao;
    private CuentaBancariaDependencia cbd;

    @BeforeEach
    public void iniciarDao(){
        setCbd(CuentaBancariaDependencia.getInstance());
        setDao(getCbd().getCuentaBancariaDao());
    }

    @Test
    public void deberiaFallarPersistirEntidad(){
        CuentaBancaria cuenta1 = new CuentaBancaria("1111111111111111111111"
                , CuentaBancariaUtil.descripcionTipoCuentaBancaria(TipoCuenta.CUENTA_CORRIENTE));

        CuentaBancaria cuentaObtenida =  getDao().guardar(cuenta1);
        assertNull(cuentaObtenida);
    }

    @Test
    public void deberiaObtenerMovimientosCuentaBancaria(){
        CuentaBancaria cuentaBancaria = getDao().buscarPorCbu("1111111111111111111111");
        assertNotNull(cuentaBancaria);
        int cantidadMovimientosEsperado = 0;
//        int cantidadMovimientosObtenidos = getDao().obtenerMovimientos(cuentaBancaria).size();
//        assertNotNull(cantidadMovimientosObtenidos);
//        assertEquals(cantidadMovimientosEsperado, cantidadMovimientosObtenidos);
    }

    @Test
    public void deberiaActualizarSaldoCuentaBancaria(){
        CuentaBancaria cuentaBancaria = getDao().buscarPorCbu("1111111111111111111111");
        assertNotNull(cuentaBancaria);
        BigDecimal valor = cuentaBancaria.getSaldo().add(new BigDecimal(1000));
        cuentaBancaria.setSaldo(valor);
        getDao().actualizar(cuentaBancaria);
    }

    @Test
    public void deberiaDepositarDeCuentaOrigenACuentaDestino(){
        CuentaBancaria origen = getDao().buscarPorId(1L);
        CuentaBancaria destino = getDao().buscarPorId(2L);
        BigDecimal monto = BigDecimal.valueOf(500.0000);
        BigDecimal montoDespositado = BigDecimal.valueOf(1500.0000);

        if (origen != null && destino != null){
            CuentaBancariaBusiness.depositar(destino,origen, monto);
            assertEquals(monto, origen.getSaldo());
            assertEquals(montoDespositado, destino.getSaldo());
        }
    }

    public CuentaBancariaDao getDao() { return dao; }
    public void setDao(CuentaBancariaDao dao) { this.dao = dao; }
    public CuentaBancariaDependencia getCbd() { return cbd; }
    public void setCbd(CuentaBancariaDependencia cbd) { this.cbd = cbd; }
}