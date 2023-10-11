package org.core.utilidades.dependencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.core.utilidades.dao.OrganizacionDao;

public class OrganizacionDependencia {
    private static OrganizacionDependencia instance;
    private EntityManagerFactory emf;

    private OrganizacionDependencia(){
        emf = Persistence.createEntityManagerFactory("persistence_core");
    }
    public static OrganizacionDependencia getInstance(){
        if (instance == null){
            instance = new OrganizacionDependencia();
        }
        return instance;
    }
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    public OrganizacionDao getOrganizacionDao(){
        return new OrganizacionDao(getEntityManager());
    }
}
