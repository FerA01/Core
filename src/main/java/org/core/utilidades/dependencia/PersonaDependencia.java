package org.core.utilidades.dependencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.core.utilidades.dao.PersonaDao;

public class PersonaDependencia {
    private static PersonaDependencia instance;
    private EntityManagerFactory emf;

    private PersonaDependencia(){
        emf = Persistence.createEntityManagerFactory("persistence_core");
    }
    public static PersonaDependencia getInstance(){
        if (instance == null){
            instance = new PersonaDependencia();
        }
        return instance;
    }
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    public PersonaDao getPersonaDao(){
        return new PersonaDao(getEntityManager());
    }
}
