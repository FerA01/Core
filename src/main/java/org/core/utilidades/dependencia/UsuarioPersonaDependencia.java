package org.core.utilidades.dependencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.core.utilidades.dao.UsuarioPersonaDao;

public class UsuarioPersonaDependencia {
    private static UsuarioPersonaDependencia instance;
    private  EntityManagerFactory emf;

    private UsuarioPersonaDependencia(){
        emf = Persistence.createEntityManagerFactory("persistence_core");
    }

    public static UsuarioPersonaDependencia getInstance(){
        if (instance == null){
            instance = new UsuarioPersonaDependencia();
        }
        return instance;
    }
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    public UsuarioPersonaDao getUsuarioPersonaDao(){
        return new UsuarioPersonaDao(getEntityManager());
    }
}
