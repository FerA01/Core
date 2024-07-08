package org.core.utilidades.dao.cuentabancaria;
import jakarta.persistence.*;
import org.core.utilidades.dao.AbstractDao;
import org.core.utilidades.dao.UsuarioOrganizacionDao;
import org.core.utilidades.entity.cuentabancaria.CuentaBancaria;
import org.core.utilidades.entity.cuentabancaria.Movimiento;
import org.core.utilidades.util.exception.NoExisteCuentaBancariaException;
import java.util.List;

public class CuentaBancariaDao extends AbstractDao<CuentaBancaria> {
    private static CuentaBancariaDao abstractDao;
    public CuentaBancariaDao(){ super(); }
    public CuentaBancariaDao(EntityManager em){ super(em); }

    public static CuentaBancariaDao getInstance(EntityManager em){
        if (AbstractDao.abstractDao == null){
            abstractDao = new CuentaBancariaDao(em);
        }
        return abstractDao;
    }

    public CuentaBancaria buscarPorId(Long id) { return super.buscarPorId(CuentaBancaria.class, id); }

    public List<CuentaBancaria> obtenerTodo(){ return super.obtenerTodo(CuentaBancaria.class); }

    public CuentaBancaria buscarPorCbu(String cbu){
        TypedQuery<CuentaBancaria> query = entityManager.createNamedQuery("CuentaBancaria.findByCbu", CuentaBancaria.class);
        query.setParameter("cbu", cbu);
        CuentaBancaria cuentaBancaria = null;
        try {
            cuentaBancaria = query.getSingleResult();
        }catch (PersistenceException e){
            logger.severe(e.getMessage());
        }
        if (cuentaBancaria == null){
            throw new NoExisteCuentaBancariaException(cbu);
        }
        return cuentaBancaria;
    }
    public List<Movimiento> obtenerMovimientos(CuentaBancaria cuentaBancaria){
        TypedQuery<Movimiento> query = entityManager.createNamedQuery("Movimiento.findByCuentaOrigen", Movimiento.class);
        query.setParameter("cuentaOrigen", cuentaBancaria);
        return query.getResultList();
    }

    @Override
    protected void beforeCreate(CuentaBancaria entity) throws EntityExistsException{
        try{
            CuentaBancaria cuentaBancaria = buscarPorCbu(entity.getCbu());
            if (cuentaBancaria != null){
                throw new EntityExistsException("Ya existe entidad CuentaBancaria con cbu: " + entity.getCbu());
            }
        }catch (NoExisteCuentaBancariaException e){
            super.beforeCreate(entity);
        }
    }
}
