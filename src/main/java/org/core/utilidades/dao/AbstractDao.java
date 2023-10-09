package org.core.utilidades.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.logging.Logger;

public class AbstractDao<T>{
    private static final Logger logger = Logger.getLogger(AbstractDao.class.getName());
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(getEntity().getClass().getName()+"Dao");
    EntityManager em = emf.createEntityManager();
    private T entity;

    public AbstractDao(){}

    public List<T> findAllBy(String query, Object params){
        //ArrayList params =
//        em.getTransaction();
//        Query query1 = em.createQuery(query).setParameter(params);
//        return query1.getResultList();
        return null;
    }



    public T getEntity() { return entity; }
    public void setEntity(T entity) { this.entity = entity; }
}
