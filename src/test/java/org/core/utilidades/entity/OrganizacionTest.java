package org.core.utilidades.entity;

import org.core.utilidades.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizacionTest {

    @Test
    public void deberiaDevolverDatosOrganizacion(){
        String datoEsperado = "Razón Social: Ferrari SA, Fecha de creación: 09/10/2023, Cuit: 30123456789";
        Organizacion organizacion = new Organizacion();
        organizacion.setRazonSocial("Ferrari SA");
        organizacion.setFechaCreacion(Util.cambiarFecha(Util.getFechaHoy(),9,10,2023));
        organizacion.setCuit(30123456789L);

        String datoObtenido = organizacion.toString();

        assertEquals(datoEsperado, datoObtenido);
    }

}