package org.core.utilidades.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Manager {
    private static EntityManagerFactory emf;

    public Manager(){}

    public static EntityManagerFactory getEmf(){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("persistence_core");
        }
        return emf;
    }

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
