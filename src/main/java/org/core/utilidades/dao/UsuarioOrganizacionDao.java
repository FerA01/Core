package org.core.utilidades.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.core.utilidades.entity.Organizacion;
import org.core.utilidades.entity.UsuarioOrganizacion;

public class UsuarioOrganizacionDao extends AbstractDao<UsuarioOrganizacion> {
    public UsuarioOrganizacionDao(){super();}
    public UsuarioOrganizacionDao(EntityManager em){ super(em);}

    //Antes de crear el usuario organización debo asegurar que exista la organización.
    public void beforeCreate(){

    }


    public UsuarioOrganizacion buscarPorId(Long id) {
        return super.buscarPorId(UsuarioOrganizacion.class, id);
    }

    @Override
    public UsuarioOrganizacion guardar(UsuarioOrganizacion entidad) {
        return super.guardar(entidad);
    }

    public UsuarioOrganizacion obtenerPorOrganizacion(Organizacion organizacion){
        TypedQuery<UsuarioOrganizacion> query = entityManager.createNamedQuery("UsuarioOrganizacion.findByOrganizacion", UsuarioOrganizacion.class);
        query.setParameter("organizacion", organizacion);
        return query.getSingleResult();
    }
}
