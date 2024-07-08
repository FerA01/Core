package org.core.utilidades.business;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.core.utilidades.dao.PersonaDao;
import org.core.utilidades.dao.UsuarioPersonaDao;
import org.core.utilidades.entity.Persona;
import org.core.utilidades.entity.UsuarioPersona;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioPersonaBusiness {
    protected static final Logger logger = Logger.getLogger(UsuarioPersonaBusiness.class.getName());
    private static PersonaDao personaDao;
    private static UsuarioPersonaDao usuarioPersonaDao;

    public static void crearUsuario(Persona persona, UsuarioPersona usuario, EntityManager entityManager){
        personaDao = PersonaDao.getInstance(entityManager);
        usuarioPersonaDao = UsuarioPersonaDao.getInstance(entityManager);
        try {
            Persona persona1 = personaDao.guardar(persona);
            usuario.setPersona(persona1);
            usuarioPersonaDao.guardar(usuario);

        }catch (EntityExistsException | NullPointerException | NoResultException e){
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
