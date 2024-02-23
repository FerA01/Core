package org.core.utilidades.entity.cuentabancaria;
import org.core.utilidades.dao.cuentabancaria.MovimientoDao;
import org.core.utilidades.dependencia.cuentabancaria.MovimientoDependencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Date;
import static org.mockito.Mockito.mock;

class MovimientoTest {
    private MovimientoDependencia mdep;
    private MovimientoDao md;

    @BeforeEach
    @Test
    private void iniciarDao(){
        setMdep(MovimientoDependencia.getInstance());
        setMd(getMdep().getOrganizacionDao());
    }

    /**
     *  El test deberia fallar al no ingresar una cuenta de origen y destino, es decir, ambas son nulas.
     *
     *  Nota: El null en este caso se debe a que no existen ambas cuentas en la base de datos
     * **/
    @Test
    @Disabled
    public void deberiaFallarPersistenciaEntidad(){
        CuentaBancaria cuentaOrigen = mock(CuentaBancaria.class);
        CuentaBancaria cuentaDestino = mock(CuentaBancaria.class);
        Date fechaMovimiento = mock(Date.class);
        TipoTransaccion tipoTransaccion = mock(TipoTransaccion.class);
        tipoTransaccion.setNombre("DEPOSITO");
        Movimiento movimiento = new Movimiento(BigDecimal.valueOf(10), fechaMovimiento, tipoTransaccion, cuentaOrigen, cuentaDestino);
        System.out.println(movimiento);
    }

    @Test
    public void fecha(){

    }

    public MovimientoDependencia getMdep() { return mdep; }
    public void setMdep(MovimientoDependencia mdep) { this.mdep = mdep; }
    public MovimientoDao getMd() { return md; }
    public void setMd(MovimientoDao md) { this.md = md; }
}