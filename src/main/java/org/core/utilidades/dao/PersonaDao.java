package org.core.utilidades.dao;

import jakarta.persistence.EntityManager;
import org.core.utilidades.entity.Persona;

public class PersonaDao extends AbstractDao<Persona>{
    public PersonaDao(){
        super();
    }
    public PersonaDao(EntityManager em){
        super(em);
    }
}
