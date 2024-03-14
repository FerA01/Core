package org.core.utilidades.dependencia.cuentabancaria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.core.utilidades.dao.cuentabancaria.MovimientoDao;

public class MovimientoDependencia {
    private static MovimientoDependencia instance;
    private EntityManagerFactory emf;

    private MovimientoDependencia(){
        emf = Persistence.createEntityManagerFactory("persistence_core");
    }
    public static MovimientoDependencia getInstance(){
        if (instance == null){
            instance = new MovimientoDependencia();
        }
        return instance;
    }
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    public MovimientoDao getMovimientoDao(){
        return new MovimientoDao(getEntityManager());
    }
}
