package org.core.utilidades.dao.cuentabancaria;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import org.core.utilidades.dao.AbstractDao;
import org.core.utilidades.entity.cuentabancaria.TipoTransaccion;

import java.util.List;

public class TipoTransaccionDao extends AbstractDao<TipoTransaccion> {
    public TipoTransaccionDao(){ super(); }
    public TipoTransaccionDao(EntityManager em){ super(em); }

    public TipoTransaccion buscarPorId(Long id){ return super.buscarPorId(TipoTransaccion.class, id); }
    public TipoTransaccion buscarPorNombre(String nombre){
        Query query = this.entityManager.createNamedQuery("TipoTransaccion.findByNombre");
        query.setParameter("nombre", nombre);
        return (TipoTransaccion) query.getSingleResult();
    }
    public List<TipoTransaccion> obtenerTodo(){ return super.obtenerTodo(TipoTransaccion.class); }

    @Override
    protected void beforeCreate(TipoTransaccion entity) throws PersistenceException {
        TipoTransaccion existe = buscarPorNombre(entity.getNombre());
        if (existe != null){
            throw new EntityExistsException("Ya existe la entidad con nombre " + existe.getNombre());
        }
        super.beforeCreate(entity);
    }
}
