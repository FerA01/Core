package org.core.utilidades.dao;
import org.core.utilidades.dependencia.PersonaDependencia;
import org.core.utilidades.entity.Persona;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonaDaoTest {
    private PersonaDao personaDao;
    private PersonaDependencia pd;

    @Test
    public void deberiaDevolverPersonaIdUno(){
        setPd(PersonaDependencia.getInstance());
        setPersonaDao(getPd().getPersonaDao());

        Persona personaObtenida = getPersonaDao().buscarPorId(Persona.class, 1L);
//      TypedQuery<Persona> query = getPd().getEntityManager().createNamedQuery("Persona.findById", Persona.class);
//      query.setParameter("id", 1L);
//      Persona personaEsperada = query.getSingleResult();
//      getPd().getEntityManager().close();
//      getPd().getEntityManager().getEntityManagerFactory().close();
//      assertEquals(personaEsperada.toString(), personaObtenida.toString());
        assertEquals(1L, personaObtenida.getId());
    }

    public PersonaDao getPersonaDao() { return personaDao; }
    public void setPersonaDao(PersonaDao personaDao) { this.personaDao = personaDao; }
    public PersonaDependencia getPd() { return pd; }
    public void setPd(PersonaDependencia pd) { this.pd = pd; }
}