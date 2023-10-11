package org.core.utilidades.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.core.utilidades.entity.Organizacion;
import org.core.utilidades.entity.Titular;

public class OrganizacionDao extends AbstractDao<Organizacion> implements Titular {

    public OrganizacionDao(){}
    public OrganizacionDao(EntityManager em){
        super(em);
    }

    @Override
    public Organizacion obtenerTitularCuit(Long cuit) {
        TypedQuery<Organizacion> query = entityManager.createNamedQuery("Organizacion.findByCuit", Organizacion.class);
        query.setParameter("cuit", cuit);
        return query.getSingleResult();
    }

    public Organizacion buscarPorId(Long id){
        return super.buscarPorId(Organizacion.class, id);
    }
}
