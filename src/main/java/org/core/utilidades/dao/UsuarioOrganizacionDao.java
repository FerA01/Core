package org.core.utilidades.dao;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.core.utilidades.entity.Organizacion;
import org.core.utilidades.entity.Usuario;
import org.core.utilidades.entity.UsuarioOrganizacion;
import org.core.utilidades.entity.UsuarioPersona;

public class UsuarioOrganizacionDao extends AbstractDao<UsuarioOrganizacion> implements Usuario {
    private static UsuarioOrganizacionDao abstractDao;
    public UsuarioOrganizacionDao(){super();}
    public UsuarioOrganizacionDao(EntityManager em){ super(em);}

    public static UsuarioOrganizacionDao getInstance(EntityManager em){
        if (AbstractDao.abstractDao == null){
            abstractDao = new UsuarioOrganizacionDao(em);
        }
        return abstractDao;
    }

    public UsuarioOrganizacion buscarPorId(Long id) {
        return super.buscarPorId(UsuarioOrganizacion.class, id);
    }

    @Override
    public UsuarioOrganizacion guardar(UsuarioOrganizacion entidad) {
        return super.guardar(entidad);
    }

    public UsuarioOrganizacion buscarPorUsuario(String usuario){
        TypedQuery<UsuarioOrganizacion> query = entityManager.createNamedQuery("UsuarioOrganizacion.findByUsuario", UsuarioOrganizacion.class);
        query.setParameter("usuario", usuario);
        return query.getSingleResult();
    }

    public UsuarioOrganizacion obtenerPorOrganizacion(Organizacion organizacion){
        TypedQuery<UsuarioOrganizacion> query = entityManager.createNamedQuery("UsuarioOrganizacion.findByOrganizacion", UsuarioOrganizacion.class);
        query.setParameter("organizacion", organizacion);
        return query.getSingleResult();
    }

    @Override
    protected void beforeCreate(UsuarioOrganizacion entity) {
        try {
            UsuarioOrganizacion usuario = obtenerPorOrganizacion(entity.getOrganizacion());
            if (usuario != null){
                throw new EntityExistsException("La entidad ya se encuentra creada.");
            }
            super.beforeCreate(entity);
        }catch (NoResultException | NullPointerException e){
            super.beforeCreate(entity);
        }
    }
}
