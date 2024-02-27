package org.core.utilidades.business;
import org.core.utilidades.dao.cuentabancaria.CuentaBancariaDao;
import org.core.utilidades.dependencia.cuentabancaria.CuentaBancariaDependencia;
import org.core.utilidades.entity.cuentabancaria.CuentaBancaria;
import org.core.utilidades.util.exception.SinSaldoDisponibleException;

import static org.core.utilidades.util.operaciones.CuentaBancariaUtil.*;
import java.math.BigDecimal;

public class CuentaBancariaBusiness {
    public static final BigDecimal LIMITE_CUENTA_CORRIENTE = BigDecimal.valueOf(-12000L);
    public static final BigDecimal LIMITE_CAJA_AHORRO = BigDecimal.valueOf(-5000L);
    public static final BigDecimal LIMITE_CUENTA_SUELDO = BigDecimal.ZERO;
    public static final BigDecimal LIMITE_CUENTA_REMUNERADA = BigDecimal.ZERO;
    public static final BigDecimal LIMITE_CUENTA_MONEDA_EXTRANJERA = BigDecimal.ZERO;

    private static CuentaBancariaDependencia dependencia;
    private static CuentaBancariaDao dao;

    private static void iniciarDao(){
        setDependencia(CuentaBancariaDependencia.getInstance());
        setDao(getDependencia().getCuentaBancariaDao());
    }

    public static void depositar(CuentaBancaria origen, CuentaBancaria destino, BigDecimal monto) throws SinSaldoDisponibleException {
        iniciarDao();
        BigDecimal verificarSaldoPositivo = restar(origen.getSaldo(), monto);
        if (saldoMayorIgualA(verificarSaldoPositivo, origen.limiteDescubierto())){
            //Tiene saldo y no supero el descubierto
            try {
                origen.setSaldo(verificarSaldoPositivo);
                destino.setSaldo(sumar(destino.getSaldo(), monto));

                getDao().actualizar(origen);
                getDao().actualizar(destino);
                return;
            }catch (Exception e){
                e.getStackTrace();
            }
        }
        throw new SinSaldoDisponibleException(monto);
    }
    public static CuentaBancariaDependencia getDependencia() { return dependencia; }
    public static void setDependencia(CuentaBancariaDependencia dependencia) { CuentaBancariaBusiness.dependencia = dependencia; }
    public static CuentaBancariaDao getDao() { return dao; }
    public static void setDao(CuentaBancariaDao dao) { CuentaBancariaBusiness.dao = dao; }
}
