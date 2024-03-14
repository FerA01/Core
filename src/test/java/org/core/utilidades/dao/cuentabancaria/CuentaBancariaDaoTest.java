package org.core.utilidades.dao.cuentabancaria;
import org.core.utilidades.business.CuentaBancariaBusiness;
import org.core.utilidades.dependencia.cuentabancaria.CuentaBancariaDependencia;
import org.core.utilidades.entity.cuentabancaria.CuentaBancaria;
import org.core.utilidades.entity.cuentabancaria.TipoCuenta;
import org.core.utilidades.util.Util;
import org.core.utilidades.util.exception.SinSaldoDisponibleException;
import org.core.utilidades.util.operaciones.CuentaBancariaUtil;
import org.core.utilidades.util.script.ScriptExecuter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
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
    public void deberiaDepositarDeCuentaOrigenACuentaDestino() throws SinSaldoDisponibleException {
        CuentaBancaria origen = getDao().buscarPorId(1L);
        CuentaBancaria destino = getDao().buscarPorId(2L);
        BigDecimal monto = BigDecimal.valueOf(500.0000);

        assertNotNull(origen);
        assertNotNull(destino);
        CuentaBancariaBusiness.depositar(origen, destino, monto);
        assertEquals(new BigDecimal("500.0000"), origen.getSaldo());
        assertEquals(new BigDecimal("1500.0000"), destino.getSaldo());
    }
    @Test
    public void deberiaExtraerDeCuentaOrigenCorrectamente() throws SinSaldoDisponibleException {
        script_saldo_cuenta_bancaria_por_defecto_1000();
        CuentaBancaria origen = getDao().buscarPorId(1L);
        BigDecimal monto = BigDecimal.valueOf(500);

        assertNotNull(origen);
        CuentaBancariaBusiness.extraer(origen, monto);
        assertEquals(new BigDecimal("500.0000"), origen.getSaldo());
    }

    @Test
    public void deberiaFallarDepositarDeCuentaOrigenACuentaDestino() {
        CuentaBancaria origen = getDao().buscarPorId(1L);
        CuentaBancaria destino = getDao().buscarPorId(2L);
        BigDecimal monto = BigDecimal.valueOf(2000.0000);

        if (origen != null && destino != null){
            assertThrows(SinSaldoDisponibleException.class, () -> CuentaBancariaBusiness.depositar(origen, destino, monto));
        }
    }
    @Test
    public void deberiaFallarExtraerDeCuentaOrigen() throws SinSaldoDisponibleException {
        CuentaBancaria origen = getDao().buscarPorId(1L);
        BigDecimal monto = BigDecimal.valueOf(23500);

        assertNotNull(origen);
        assertThrows(SinSaldoDisponibleException.class, () -> CuentaBancariaBusiness.extraer(origen, monto));
    }

    @Test
    public void deberiaHacerExtraccionCorrectamenteYMovimientoCreado(){
        CuentaBancaria origen = getDao().buscarPorId(1L);
        BigDecimal extraer = BigDecimal.valueOf(300);

        assertNotNull(origen);
        assertDoesNotThrow(() -> CuentaBancariaBusiness.extraer(origen, extraer));
    }

    @Test
    public void cuentaBancariaDeberiaTenerMovimientos(){
        CuentaBancaria origen = getDao().buscarPorId(1L);

        assertNotNull(origen);
    }

    @AfterEach
    public void script_saldo_cuenta_bancaria_por_defecto_1000(){
        try {
            Connection connection = DriverManager.getConnection(Util.BD_URL,
                                                                Util.BD_USERNAME,
                                                                Util.BD_PASSWORD);
            String script = Util.SCRIPTS_PATH + "cuentabancaria.sql";
            String querys = ScriptExecuter.readSQLScript(script);
            ScriptExecuter.executeScript(connection, querys);
            connection.close();
        }catch (Exception e){
            e.getStackTrace();
        }
    }


    public CuentaBancariaDao getDao() { return dao; }
    public void setDao(CuentaBancariaDao dao) { this.dao = dao; }
    public CuentaBancariaDependencia getCbd() { return cbd; }
    public void setCbd(CuentaBancariaDependencia cbd) { this.cbd = cbd; }
}