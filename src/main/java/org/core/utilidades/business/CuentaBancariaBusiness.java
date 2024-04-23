package org.core.utilidades.business;
import jakarta.inject.Inject;
import org.core.utilidades.dao.cuentabancaria.CuentaBancariaDao;
import org.core.utilidades.dao.cuentabancaria.MovimientoDao;
import org.core.utilidades.dao.cuentabancaria.TipoTransaccionDao;
import org.core.utilidades.dependencia.cuentabancaria.CuentaBancariaDependencia;
import org.core.utilidades.dependencia.cuentabancaria.MovimientoDependencia;
import org.core.utilidades.dependencia.cuentabancaria.TipoTransaccionDependencia;
import org.core.utilidades.entity.cuentabancaria.CuentaBancaria;
import org.core.utilidades.entity.cuentabancaria.Movimiento;
import org.core.utilidades.entity.cuentabancaria.TipoTransaccion;
import org.core.utilidades.util.Util;
import org.core.utilidades.util.exception.SinSaldoDisponibleException;
import static org.core.utilidades.util.operaciones.CuentaBancariaUtil.*;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CuentaBancariaBusiness {
    public static final BigDecimal LIMITE_CUENTA_CORRIENTE = BigDecimal.valueOf(-12000L);
    public static final BigDecimal LIMITE_CAJA_AHORRO = BigDecimal.valueOf(-5000L);
    public static final BigDecimal LIMITE_CUENTA_SUELDO = BigDecimal.ZERO;
    public static final BigDecimal LIMITE_CUENTA_REMUNERADA = BigDecimal.ZERO;
    public static final BigDecimal LIMITE_CUENTA_MONEDA_EXTRANJERA = BigDecimal.ZERO;


    private static CuentaBancariaDependencia dependencia;
    private static CuentaBancariaDao dao;

    private static MovimientoDependencia movimientoDependencia;
    private static MovimientoDao movimientoDao;

    private static TipoTransaccionDependencia tipoTransaccionDependencia;
    private static TipoTransaccionDao tipoTransaccionDao;

    private static void iniciarDao(){
        setDependencia(CuentaBancariaDependencia.getInstance());
        setDao(getDependencia().getCuentaBancariaDao());
        setTipoTransaccionDependencia(TipoTransaccionDependencia.getInstance());
        setTipoTransaccionDao(getTipoTransaccionDependencia().getTipoTransaccionDao());
        setMovimientoDependencia(MovimientoDependencia.getInstance());
        setMovimientoDao(getMovimientoDependencia().getMovimientoDao());
    }

    public static void depositar(CuentaBancaria origen, CuentaBancaria destino, BigDecimal monto) throws SinSaldoDisponibleException, NullPointerException {
        iniciarDao();
        BigDecimal verificarSaldoPositivo = restar(origen.getSaldo(), monto);
        if (saldoMayorIgualA(verificarSaldoPositivo, origen.limiteDescubierto())){
            //Tiene saldo y no supero el descubierto
            try {
                origen.setSaldo(verificarSaldoPositivo);
                destino.setSaldo(sumar(destino.getSaldo(), monto));

                getDao().actualizar(origen);
                getDao().actualizar(destino);

                TipoTransaccion tipoTransaccion = getTipoTransaccionDao().buscarPorNombre("Depósito");
                if (tipoTransaccion == null){
                    throw new NullPointerException("El tipo de transacción no existe en la base");
                }
                Movimiento movimiento = new Movimiento(monto, Util.getFechaHoy(), tipoTransaccion, origen, destino);
                getMovimientoDao().guardar(movimiento);
                movimiento.getLogger().log(Level.FINE, "Movimiento creado correctamente");
                return;
            }catch (Exception e){
                origen.getLogger().log(Level.WARNING, e.getMessage());
            }
        }
        throw new SinSaldoDisponibleException(monto);
    }
    public static void extraer(CuentaBancaria origen, BigDecimal monto) throws SinSaldoDisponibleException, NullPointerException{
        iniciarDao();
        BigDecimal verificarSaldoPositivo = restar(origen.getSaldo(), monto);
        if (saldoMayorIgualA(verificarSaldoPositivo, origen.limiteDescubierto())){
            try {
                origen.setSaldo(verificarSaldoPositivo);
                getDao().actualizar(origen);

                TipoTransaccion tipoTransaccion = getTipoTransaccionDao().buscarPorNombre("Extracción");
                if (tipoTransaccion != null){
                    Movimiento movimiento = new Movimiento(monto, Util.getFechaHoy(), tipoTransaccion, origen, null);
                    getMovimientoDao().guardar(movimiento);
                    origen.getLogger().log(Level.FINE, "Saldo extraido correctamente");
                    return;
                }
                throw new NullPointerException("El tipo de transacción no existe en la base");
            }catch (Exception e){
                origen.getLogger().log(Level.WARNING, e.getMessage());
            }
        }
        throw new SinSaldoDisponibleException(monto);
    }
    public static CuentaBancariaDependencia getDependencia() { return dependencia; }
    public static void setDependencia(CuentaBancariaDependencia dependencia) { CuentaBancariaBusiness.dependencia = dependencia; }
    public static CuentaBancariaDao getDao() { return dao; }
    public static void setDao(CuentaBancariaDao dao) { CuentaBancariaBusiness.dao = dao; }
    public static MovimientoDao getMovimientoDao() { return movimientoDao; }
    public static void setMovimientoDao(MovimientoDao movimientoDao) { CuentaBancariaBusiness.movimientoDao = movimientoDao; }
    public static TipoTransaccionDao getTipoTransaccionDao() { return tipoTransaccionDao; }
    public static void setTipoTransaccionDao(TipoTransaccionDao tipoTransaccionDao) { CuentaBancariaBusiness.tipoTransaccionDao = tipoTransaccionDao; }
    public static MovimientoDependencia getMovimientoDependencia() { return movimientoDependencia; }
    public static void setMovimientoDependencia(MovimientoDependencia movimientoDependencia) { CuentaBancariaBusiness.movimientoDependencia = movimientoDependencia; }
    public static TipoTransaccionDependencia getTipoTransaccionDependencia() { return tipoTransaccionDependencia; }
    public static void setTipoTransaccionDependencia(TipoTransaccionDependencia tipoTransaccionDependencia) { CuentaBancariaBusiness.tipoTransaccionDependencia = tipoTransaccionDependencia; }
}
