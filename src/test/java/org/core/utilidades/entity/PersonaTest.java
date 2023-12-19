package org.core.utilidades.entity;
import org.core.utilidades.dao.PersonaDao;
import org.core.utilidades.dependencia.PersonaDependencia;
import org.core.utilidades.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonaTest {
    private PersonaDao personaDao;
    private PersonaDependencia pd;

    @BeforeEach
    @Test
    public void iniciarDao(){
        setPd(PersonaDependencia.getInstance());
        setPersonaDao(getPd().getPersonaDao());
    }

    @Test
    public void deberiaDevolverDatosPersona(){
        String datosPersonaEsperado = "Nombre y Apellido: Leonel Messi, DNI: 12345678, Fecha de nacimiento: 08/10/2023, Cuit: 20123456781";

        Persona personaObtenida = new Persona();
        personaObtenida.setNombre("Leonel");
        personaObtenida.setApellido("Messi");
        personaObtenida.setDni(12345678L);
        personaObtenida.setFechaNacimiento(Util.cambiarFecha(Util.getFechaHoy(),8,10,2023));
        personaObtenida.setCuit(20123456781L);
        assertEquals(datosPersonaEsperado, personaObtenida.toString());
    }

    @Test
    public void deberiaActualizarDatoPersonaNombre(){
        String nombreEsperado = "Leonel Andres";
        Persona persona = getPersonaDao().buscarPorId(1L);
        assertNotNull(persona);
        persona.setNombre("Leonel Andres");
        getPersonaDao().actualizar(persona);
        Persona actualizado = getPersonaDao().obtenerPersonaDni(12345678L);
        assertNotNull(actualizado);
        assertEquals(nombreEsperado, actualizado.getNombre());
    }

    @Test
    public void deberiaFallarPersistirEntidadExistente() {
        Persona persona = getPersonaDao().buscarPorId(1L);
        Persona obtenido = getPersonaDao().guardar(persona);
        assertNull(obtenido);
    }

    public PersonaDao getPersonaDao() { return personaDao; }
    public void setPersonaDao(PersonaDao personaDao) { this.personaDao = personaDao; }
    public PersonaDependencia getPd() { return pd; }
    public void setPd(PersonaDependencia pd) { this.pd = pd; }
}