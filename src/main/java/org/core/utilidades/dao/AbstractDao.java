package org.core.utilidades.dao;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDao<T>{
    protected final Logger logger = Logger.getLogger(getClass().getName());
    protected EntityManager entityManager;

    protected AbstractDao(){}
    protected AbstractDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected void beforeCreate(){}
    protected void afterCreate(){}
    protected void beforeUpdate(){}
    protected void afterUpdate(){}
    protected void beforeDelete(){}
    protected void afterDelete(){}

    public T guardar(T entidad) {
        try {
            entityManager.getTransaction().begin();
            beforeCreate();
            entityManager.persist(entidad);
            afterCreate();
            entityManager.getTransaction().commit();
            getLogger().log(Level.FINER, "Persistiendo " + entidad.getClass().getSimpleName());
            return entidad;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.severe("Error al guardar la entidad " + getClass().getName() + ": " +  e.getMessage());
        }finally {
            entityManager.close();
        }
        return null;
    }

    public T actualizar(T entidad) {
        try {
            entityManager.getTransaction().begin();
            beforeUpdate();
            entidad = entityManager.merge(entidad);
            afterUpdate();
            entityManager.getTransaction().commit();
            return entidad;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.severe("Error al actualizar la entidad " + getClass().getName() + ": " +  e.getMessage());
            return null;
        }
    }

    public void eliminar(T entidad) {
        try {
            entityManager.getTransaction().begin();
            beforeDelete();
            entityManager.remove(entidad);
            afterDelete();
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

    public List<T> obtenerTodo(Class<T> clase){
        return entityManager.createNamedQuery(clase.getSimpleName()+".findAll").getResultList();
    }
    public Logger getLogger() { return logger; }
}
