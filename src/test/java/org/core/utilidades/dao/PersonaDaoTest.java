package org.core.utilidades.dao;
import org.core.utilidades.dependencia.PersonaDependencia;
import org.core.utilidades.entity.Persona;
import org.core.utilidades.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonaDaoTest {
    private PersonaDao personaDao;
    private PersonaDependencia pd;

    @BeforeEach
    @Test
    public void iniciarDao(){
        setPd(PersonaDependencia.getInstance());
        setPersonaDao(getPd().getPersonaDao());
    }

    @Test
    public void deberiaDevolverPersonaIdUno(){
        Persona personaObtenida = getPersonaDao().buscarPorId(1L);
//      TypedQuery<Persona> query = getPd().getEntityManager().createNamedQuery("Persona.findById", Persona.class);
//      query.setParameter("id", 1L);
//      Persona personaEsperada = query.getSingleResult();
//      getPd().getEntityManager().close();
//      getPd().getEntityManager().getEntityManagerFactory().close();
//      assertEquals(personaEsperada.toString(), personaObtenida.toString());
        assertEquals(1L, personaObtenida.getId());
    }
    @Test
    public void deberiaPersistirPersona(){
        Persona persona = new Persona();
        persona.setNombre("Juan");
        persona.setApellido("Perez");
        persona.setDni(87654321L);
        persona.setCuit(20876543214L);
        persona.setFechaNacimiento(Util.getFechaHoy());

        Persona personaObtenida = getPersonaDao().obtenerTitularCuit(persona.getCuit());

        assertEquals(persona.toString(), personaObtenida.toString());
    }
    @Test
    public void deberiaObtenerListaPersona(){
        List<Persona> personas = getPersonaDao().obtenerTodo();
        assertTrue(personas != null && !personas.isEmpty());
    }

    @Test
    public void deberiaObtenerPersonaDni(){
        Long dniEsperado = 12345678L;
        Persona persona = getPersonaDao().obtenerPersonaDni(12345678L);
        assertEquals(dniEsperado, persona.getDni());
    }

    @Test
    public void deberiaActualizarFechaNacimientoPersona(){
        Persona persona = getPersonaDao().obtenerPersonaDni(87654321L);
        Date fechaEsperada = persona.getFechaNacimiento();
        Date fecha = Util.cambiarFecha(Util.getFechaHoy(), 1,1,2010);
        persona.setFechaNacimiento(fecha);
        Persona personaObtenida = getPersonaDao().actualizar(persona);
        assertNotEquals( Util.getFechaFormato(fechaEsperada, "dd/MM/yyyy"),
                         Util.getFechaFormato(personaObtenida.getFechaNacimiento(), "dd/MM/yyyy")
        );
    }

    @Test
    public void deberiaEliminarPersonaIdDos(){
        Persona persona = getPersonaDao().buscarPorId(2L);
        assertNull(persona);
    }

    public PersonaDao getPersonaDao() { return personaDao; }
    public void setPersonaDao(PersonaDao personaDao) { this.personaDao = personaDao; }
    public PersonaDependencia getPd() { return pd; }
    public void setPd(PersonaDependencia pd) { this.pd = pd; }
}