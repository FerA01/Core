package org.core.utilidades.dao.cuentabancaria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.core.utilidades.dao.AbstractDao;
import org.core.utilidades.entity.cuentabancaria.CuentaBancaria;
import org.core.utilidades.entity.cuentabancaria.Movimiento;
import org.core.utilidades.entity.cuentabancaria.TipoTransaccion;
import java.util.List;

public class MovimientoDao extends AbstractDao<Movimiento> {
    public MovimientoDao(){ super(); }
    public MovimientoDao(EntityManager em){ super(em); }

    public Movimiento buscarPorId(Long id) {
        return super.buscarPorId(Movimiento.class, id);
    }
    public List<Movimiento> obtenerTodo(){ return super.obtenerTodo(Movimiento.class); }
    public Movimiento buscarPorTipoTransaccion(TipoTransaccion tipoTransaccion){
        Query query = this.entityManager.createNamedQuery("Movimiento.findByTipo", Movimiento.class);
        query.setParameter("tipoTransaccion", tipoTransaccion);
        return (Movimiento) query.getSingleResult();
    }
    public Movimiento buscarPorCuentaOrigen(CuentaBancaria cuentaBancaria){
        Query query = this.entityManager.createNamedQuery("Movimiento.findByCuentaOrigen", Movimiento.class);
        query.setParameter("cuentaOrigen", cuentaBancaria);
        return (Movimiento) query.getSingleResult();
    }    public Movimiento buscarPorCuentaDestino(CuentaBancaria cuentaBancaria){
        Query query = this.entityManager.createNamedQuery("Movimiento.findByCuentaDestino", Movimiento.class);
        query.setParameter("cuentaDestino", cuentaBancaria);
        return (Movimiento) query.getSingleResult();
    }
}
