package org.core.utilidades.dao;
import jakarta.persistence.EntityManager;
import java.util.logging.Logger;

public class AbstractDao<T>{
    protected final Logger logger = Logger.getLogger(getClass().getName());
    protected EntityManager entityManager;

    protected AbstractDao(){}
    protected AbstractDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public T guardar(T entidad) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entidad);
            entityManager.getTransaction().commit();
            return entidad;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.severe("Error al guardar la entidad " + getClass().getName() + ": " +  e.getMessage());
            throw e;
        }
    }

    public T actualizar(T entidad) {
        try {
            entityManager.getTransaction().begin();
            entidad = entityManager.merge(entidad);
            entityManager.getTransaction().commit();
            return entidad;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.severe("Error al actualizar la entidad " + getClass().getName() + ": " +  e.getMessage());
            throw e;
        }
    }

    public void eliminar(T entidad) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entidad);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.severe("Error al eliminar la entidad " + getClass().getName() + ": " +  e.getMessage());
            throw e;
        }
    }

    public T buscarPorId(Class<T> clase, Long id) {
        return entityManager.find(clase, id);
    }

    // Más métodos comunes de DAO
    public Logger getLogger() { return logger; }
}
