package org.core.utilidades.dao.cuentabancaria;
import org.core.utilidades.dependencia.cuentabancaria.TipoTransaccionDependencia;
import org.core.utilidades.entity.cuentabancaria.TipoTransaccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoTransaccionDaoTest {
    private TipoTransaccionDependencia ttd;
    private TipoTransaccionDao transaccionDao;

    @BeforeEach
    public void iniciarDao(){
        setTtd(TipoTransaccionDependencia.getInstance());
        setTransaccionDao(getTtd().getTipoTransaccionDao());
    }

    @Test
    public void deberiaPersistirEntidad(){
        TipoTransaccion tipoTransaccion = new TipoTransaccion();
        tipoTransaccion.setNombre("Extracción");
        TipoTransaccion obtenido = getTransaccionDao().guardar(tipoTransaccion);
        if (obtenido == null){
            getTransaccionDao().eliminar(tipoTransaccion);
            return;
        }
    }
    @Test
    public void deberiaObtenerTipoTransaccionPorNombre(){
        TipoTransaccion tipoTransaccion = getTransaccionDao().buscarPorNombre("Extracción");
        assertNotNull(tipoTransaccion);
    }

    public TipoTransaccionDependencia getTtd() { return ttd; }
    public void setTtd(TipoTransaccionDependencia ttd) { this.ttd = ttd; }
    public TipoTransaccionDao getTransaccionDao() { return transaccionDao; }
    public void setTransaccionDao(TipoTransaccionDao transaccionDao) { this.transaccionDao = transaccionDao; }
}