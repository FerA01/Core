package org.core.utilidades.dao.cuentabancaria;
import jakarta.persistence.EntityManager;
import org.core.utilidades.dao.AbstractDao;
import org.core.utilidades.entity.cuentabancaria.Movimiento;

public class MovimientoDao extends AbstractDao<Movimiento> {
    public MovimientoDao(){ super(); }
    public MovimientoDao(EntityManager em){ super(em); }

    public Movimiento buscarPorId(Long id) {
        return super.buscarPorId(Movimiento.class, id);
    }
}
