package org.core.utilidades.dao;
import org.core.utilidades.dependencia.PersonaDependencia;
import org.core.utilidades.entity.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        assertEquals(1L, personaObtenida.getId());
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
    public void deberiaFallarPersistirEntidadExistente(){
        Long cuit = 30123456789L;

        Persona persona = getPersonaDao().obtenerTitularCuit(cuit);

        assertNotNull(persona);
        Persona esperado = getPersonaDao().guardar(persona);
        assertNull(esperado);
    }

    public PersonaDao getPersonaDao() { return personaDao; }
    public void setPersonaDao(PersonaDao personaDao) { this.personaDao = personaDao; }
    public PersonaDependencia getPd() { return pd; }
    public void setPd(PersonaDependencia pd) { this.pd = pd; }
}