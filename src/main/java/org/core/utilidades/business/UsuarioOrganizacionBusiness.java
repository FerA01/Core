package org.core.utilidades.business;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.core.utilidades.dao.OrganizacionDao;
import org.core.utilidades.dao.UsuarioOrganizacionDao;
import org.core.utilidades.entity.Organizacion;
import org.core.utilidades.entity.UsuarioOrganizacion;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioOrganizacionBusiness {
    protected static final Logger logger = Logger.getLogger(UsuarioPersonaBusiness.class.getName());
    private static OrganizacionDao organizacionDao;
    private static UsuarioOrganizacionDao usuarioOrganizacionDao;

    public static void crearUsuario(Organizacion organizacion, UsuarioOrganizacion usuarioOrganizacion, EntityManager entityManager){
        organizacionDao = OrganizacionDao.getInstance(entityManager);
        usuarioOrganizacionDao = UsuarioOrganizacionDao.getInstance(entityManager);
        try{
            Organizacion organizacion1 = organizacionDao.guardar(organizacion);
            usuarioOrganizacion.setOrganizacion(organizacion1);
            usuarioOrganizacionDao.guardar(usuarioOrganizacion);

        }catch (EntityExistsException | NullPointerException | NoResultException e){
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
