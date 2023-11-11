package org.core.utilidades.dependencia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.core.utilidades.dao.UsuarioOrganizacionDao;

public class UsuarioOrganizacionDependencia {
    private static UsuarioOrganizacionDependencia instance;
    private EntityManagerFactory emf;

    private UsuarioOrganizacionDependencia(){
        emf = Persistence.createEntityManagerFactory("persistence_core");
    }

    public static UsuarioOrganizacionDependencia getInstance(){
        if (instance == null){
            instance = new UsuarioOrganizacionDependencia();
        }
        return instance;
    }
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    public UsuarioOrganizacionDao getUsuarioOrganizacionDao(){
        return new UsuarioOrganizacionDao(getEntityManager());
    }
}
