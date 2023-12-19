package org.core.utilidades.dao;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.core.utilidades.entity.Persona;
import org.core.utilidades.entity.Titular;
import org.eclipse.persistence.internal.oxm.NullCapableValue;

import java.util.List;
import java.util.logging.Level;

public class PersonaDao extends AbstractDao<Persona> implements Titular {
    public PersonaDao(){
        super();
    }
    public PersonaDao(EntityManager em){
        super(em);
    }


    public Persona buscarPorId(Long id) {
        return super.buscarPorId(Persona.class, id);
    }

    public Persona obtenerPersonaDni(Long dni){
        TypedQuery<Persona> query = entityManager.createNamedQuery("Persona.findByDni", Persona.class);
        query.setParameter("dni", dni);
        return query.getSingleResult();
    }

    public List<Persona> obtenerTodo(){
        return super.obtenerTodo(Persona.class);
    }

    @Override
    public Persona obtenerTitularCuit(Long cuit) {
        TypedQuery<Persona> query = entityManager.createNamedQuery("Persona.findByCuit", Persona.class);
        query.setParameter("cuit", cuit);
        return query.getSingleResult();
    }

    @Override
    public Persona actualizar(Persona entidad) {
        return super.actualizar(entidad);
    }

    @Override
    public Persona guardar(Persona entidad) throws NullPointerException, EntityExistsException{
        try {
            entityManager.getTransaction().begin();
            beforeCreate(entidad);
            entityManager.persist(entidad);
            afterCreate();
            entityManager.getTransaction().commit();
            getLogger().log(Level.FINER, "Persistiendo " + entidad.getClass().getSimpleName());
            return entidad;
        } catch (NullPointerException|EntityExistsException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.severe("Error al guardar la entidad " + getClass().getName() + ": " +  e.getMessage());
        }finally {
            entityManager.close();
        }
        return null;
    }

    protected void beforeCreate(Persona persona){
        TypedQuery<Persona> query = entityManager.createNamedQuery("Persona.findByCuitOrDni", Persona.class);
        query.setParameter("dni", persona.getDni());
        query.setParameter("cuit", persona.getCuit());
        Persona personaResultado = query.getSingleResult();
        if (personaResultado != null){
            throw new EntityExistsException("La entidad ya se encuentra creada.");
        }
    }
}
