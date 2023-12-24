package org.core.utilidades.dependencia.cuentabancaria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.core.utilidades.dao.cuentabancaria.TipoTransaccionDao;

public class TipoTransaccionDependencia {
    private static TipoTransaccionDependencia instance;
    private EntityManagerFactory emf;

    private TipoTransaccionDependencia(){
        emf = Persistence.createEntityManagerFactory("persistence_core");
    }
    public static TipoTransaccionDependencia getInstance(){
        if (instance == null){
            instance = new TipoTransaccionDependencia();
        }
        return instance;
    }
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    public TipoTransaccionDao getTipoTransaccionDao(){
        return new TipoTransaccionDao(getEntityManager());
    }
}
