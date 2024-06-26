package org.core.utilidades.dao;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.core.utilidades.entity.Organizacion;
import org.core.utilidades.entity.Titular;
import java.util.List;

public class OrganizacionDao extends AbstractDao<Organizacion> implements Titular {

    public OrganizacionDao(){}
    public OrganizacionDao(EntityManager em){
        super(em);
    }

    @Override
    protected void beforeCreate(Organizacion entity) {
        Organizacion organizacion = obtenerTitularCuit(entity.getCuit());
        if (organizacion != null){
            throw new EntityExistsException("Entidad ya creada");
        }
        super.beforeCreate(entity);
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

    public Organizacion  buscarPorRazonSocial(String razonSocial){
        TypedQuery<Organizacion> query = entityManager.createNamedQuery("Organizacion.findByRazonSocial", Organizacion.class);
        query.setParameter("razonSocial", razonSocial);
        return query.getSingleResult();
    }
    public List<Organizacion> obtenerTodo(){
        return super.obtenerTodo(Organizacion.class);
    }
}
