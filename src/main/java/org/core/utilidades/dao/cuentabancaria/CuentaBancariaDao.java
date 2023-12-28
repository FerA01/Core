package org.core.utilidades.dao.cuentabancaria;
import jakarta.persistence.*;
import org.core.utilidades.dao.AbstractDao;
import org.core.utilidades.entity.cuentabancaria.CuentaBancaria;
import org.core.utilidades.entity.cuentabancaria.Movimiento;

import java.math.BigInteger;
import java.util.List;

public class CuentaBancariaDao extends AbstractDao<CuentaBancaria> {

    public CuentaBancariaDao(){ super(); }
    public CuentaBancariaDao(EntityManager em){ super(em); }

    public CuentaBancaria buscarPorId(Long id) { return super.buscarPorId(CuentaBancaria.class, id); }

    public List<CuentaBancaria> obtenerTodo(){ return super.obtenerTodo(CuentaBancaria.class); }

    public CuentaBancaria buscarPorCbu(String cbu){
        TypedQuery<CuentaBancaria> query = entityManager.createNamedQuery("CuentaBancaria.findByCbu", CuentaBancaria.class);
        query.setParameter("cbu", cbu);
        return query.getSingleResult();
    }
    public List<Movimiento> obtenerMovimientos(CuentaBancaria cuentaBancaria){
        TypedQuery<Movimiento> query = entityManager.createNamedQuery("Movimiento.findByCuentaOrigen", Movimiento.class);
        query.setParameter("cuentaOrigen", cuentaBancaria);
        return query.getResultList();
    }

    @Override
    protected void beforeCreate(CuentaBancaria entity){
        try {
            buscarPorCbu(entity.getCbu());
            throw new EntityExistsException("Ya existe entidad CuentaBancaria con cbu: " + entity.getCbu());
        }catch (NoResultException e){
            super.beforeCreate(entity);
        }
    }
}
