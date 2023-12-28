package org.core.utilidades.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

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
    protected void beforeCreate(T entity){}
    protected void afterCreate(){}
    protected void afterCreate(T entity){}
    protected void beforeUpdate(){}
    protected void beforeUpdate(T entity){}
    protected void afterUpdate(){}
    protected void afterUpdate(T entity){}
    protected void beforeDelete(){}
    protected void beforeDelete(T entity){}
    protected void afterDelete(){}
    protected void afterDelete(T entity){}

    public T guardar(T entidad){
        try {
            entityManager.getTransaction().begin();
            beforeCreate();
            beforeCreate(entidad);
            entityManager.persist(entidad);
            afterCreate();
            afterCreate(entidad);
            entityManager.getTransaction().commit();
            getLogger().log(Level.FINER, "Persistiendo " + entidad.getClass().getSimpleName());
            return entidad;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.severe("Error al persistir entidad " + entidad.getClass().getSimpleName() + ": " +  e.getMessage());
        }
        return null;
    }

    public T actualizar(T entidad) {
        try {
            entityManager.getTransaction().begin();
            beforeUpdate();
            beforeUpdate(entidad);
            entidad = entityManager.merge(entidad);
            afterUpdate();
            afterUpdate(entidad);
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
            beforeDelete(entidad);
            entityManager.remove(entidad);
            afterDelete();
            afterDelete(entidad);
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
