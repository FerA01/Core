package org.core.utilidades.dao.cuentabancaria;
import org.core.utilidades.dependencia.cuentabancaria.MovimientoDependencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovimientoDaoTest {
    private MovimientoDao dao;
    private MovimientoDependencia md;


    @BeforeEach
    public void iniciarDao(){
        setMd(MovimientoDependencia.getInstance());
        setDao(getMd().getOrganizacionDao());
    }

    @Test
    public void deberiaPersistirEntidad(){

    }

    public MovimientoDao getDao() { return dao; }
    public void setDao(MovimientoDao dao) { this.dao = dao; }
    public MovimientoDependencia getMd() { return md; }
    public void setMd(MovimientoDependencia md) { this.md = md; }
}