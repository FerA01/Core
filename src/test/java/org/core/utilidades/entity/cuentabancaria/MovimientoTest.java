package org.core.utilidades.entity.cuentabancaria;
import org.core.utilidades.dao.cuentabancaria.MovimientoDao;
import org.core.utilidades.dependencia.cuentabancaria.MovimientoDependencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.Date;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovimientoTest {
    private MovimientoDependencia mdep;
    private MovimientoDao md;
    private MovimientoDao daoMock = mock(MovimientoDao.class);

    @BeforeEach
    @Test
    private void iniciarDao(){
        setMdep(MovimientoDependencia.getInstance());
        setMd(getMdep().getOrganizacionDao());
    }
    @Test
    public void deberiaPersistirMovimiento(){
        CuentaBancaria cuentaOrigen = mock(CuentaBancaria.class);
        CuentaBancaria cuentaDestino = mock(CuentaBancaria.class);
        Date fechaMovimiento = mock(Date.class);
        TipoTransaccion tipoTransaccion = mock(TipoTransaccion.class);
        Movimiento movimientoMock = new Movimiento(BigDecimal.valueOf(100), fechaMovimiento, tipoTransaccion, cuentaOrigen, cuentaDestino);

        when(daoMock.guardar(any())).thenReturn(movimientoMock);

        assertEquals(movimientoMock, daoMock.guardar(movimientoMock));
    }

    @Test
    public void errorPersistenciaCuentaOrigenNullPointerException(){
        CuentaBancaria cuentaDestino = mock(CuentaBancaria.class);
        Date fechaMovimiento = mock(Date.class);
        TipoTransaccion tipoTransaccion = mock(TipoTransaccion.class);
        Movimiento movimientoMock = new Movimiento(BigDecimal.valueOf(100), fechaMovimiento, tipoTransaccion, null, cuentaDestino);

        when(daoMock.guardar(movimientoMock)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> daoMock.guardar(movimientoMock));
    }

    @Test
    public void errorPersistenciaCuentaDestinoNullPointerException(){
        CuentaBancaria cuentaOrigen = mock(CuentaBancaria.class);
        Date fechaMovimiento = mock(Date.class);
        TipoTransaccion tipoTransaccion = mock(TipoTransaccion.class);
        Movimiento movimientoMock = new Movimiento(BigDecimal.valueOf(100), fechaMovimiento, tipoTransaccion, cuentaOrigen, null);

        when(daoMock.guardar(movimientoMock)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> daoMock.guardar(movimientoMock));
    }

    public MovimientoDependencia getMdep() { return mdep; }
    public void setMdep(MovimientoDependencia mdep) { this.mdep = mdep; }
    public MovimientoDao getMd() { return md; }
    public void setMd(MovimientoDao md) { this.md = md; }
}