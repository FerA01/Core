package org.core.utilidades.entity.cuentabancaria;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceException;
import org.core.utilidades.dao.cuentabancaria.TipoTransaccionDao;
import org.core.utilidades.dependencia.cuentabancaria.TipoTransaccionDependencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class TipoTransaccionTest {
    private TipoTransaccionDependencia ttd;
    private TipoTransaccionDao dao;

    @Test
    @BeforeEach
    private void iniciarDao(){
        setTtd(TipoTransaccionDependencia.getInstance());
        setDao(getTtd().getTipoTransaccionDao());
    }

    /**
     *      tipo_transaccion {1: EXTRACCION; 2: DEPOSITO}
     * **/
    @Test
    public void persistenceErrorYaExisteTipoTransaccion(){
        TipoTransaccion tipo = mock(TipoTransaccion.class);
        TipoTransaccionDao daoMock = mock(TipoTransaccionDao.class);
        tipo.setNombre("EXTRACCION");
        when(daoMock.guardar(tipo)).thenThrow(new EntityExistsException("Ya existe la entidad con nombre EXTRACCION"));
        assertThrows(EntityExistsException.class, () -> daoMock.guardar(tipo));
    }

    @Test
    public void nullPointerExceptionTipoTransaccion(){
        TipoTransaccionDao daoMock = mock(TipoTransaccionDao.class);
        when(daoMock.guardar(null)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, ()-> daoMock.guardar(null));
    }

    public void guardar(){

    }

    public TipoTransaccionDependencia getTtd() { return ttd; }
    public void setTtd(TipoTransaccionDependencia ttd) { this.ttd = ttd; }
    public TipoTransaccionDao getDao() { return dao; }
    public void setDao(TipoTransaccionDao dao) { this.dao = dao; }
}