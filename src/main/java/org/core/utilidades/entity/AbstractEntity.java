package org.core.utilidades.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.core.utilidades.dao.AbstractDao;

import java.util.logging.Level;
import java.util.logging.Logger;
public class AbstractEntity {
    static Logger logger = Logger.getLogger(AbstractEntity.class.getName());


    private AbstractDao<AbstractEntity> abstractDao;

    public AbstractEntity(){
        logger.log(Level.FINE, "Hola");
    }

    public AbstractDao<AbstractEntity> getAbstractDao() { return abstractDao; }
}
