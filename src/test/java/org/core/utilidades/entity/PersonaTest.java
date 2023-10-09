package org.core.utilidades.entity;
import org.core.utilidades.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonaTest {
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
}