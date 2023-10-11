package org.core.utilidades.dependencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.core.utilidades.dao.AbstractDao;
import org.core.utilidades.dao.OrganizacionDao;

public class AbstractDependencia{
    private static AbstractDependencia instance;
    private EntityManagerFactory emf;

    private AbstractDependencia(){
        emf = Persistence.createEntityManagerFactory("persistence_core");
    }
    public static AbstractDependencia getInstance(){
        if (instance == null){
            instance = new AbstractDependencia();
        }
        return instance;
    }
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }


}
