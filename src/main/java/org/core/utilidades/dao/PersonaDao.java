package org.core.utilidades.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.core.utilidades.entity.Persona;
import org.core.utilidades.entity.Titular;

import java.util.List;

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
}
