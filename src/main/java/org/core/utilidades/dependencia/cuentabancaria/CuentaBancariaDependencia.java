package org.core.utilidades.dependencia.cuentabancaria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.core.utilidades.dao.cuentabancaria.CuentaBancariaDao;

public class CuentaBancariaDependencia {
    private static CuentaBancariaDependencia instance;
    private EntityManagerFactory emf;

    private CuentaBancariaDependencia(){ emf = Persistence.createEntityManagerFactory("persistence_core"); }

    public static CuentaBancariaDependencia getInstance(){
        if (instance == null){
            instance = new CuentaBancariaDependencia();
        }
        return instance;
    }
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    public CuentaBancariaDao getCuentaBancariaDao(){
        return new CuentaBancariaDao(getEntityManager());
    }
}
