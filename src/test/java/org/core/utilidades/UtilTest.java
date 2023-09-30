package org.core.utilidades;

import org.core.utilidades.util.Util;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UtilTest {
    private final Date fechaHoy = Calendar.getInstance().getTime();

    @Test
    public void deberiaDevolverFormatoddMMyyyy(){
        String fechaEsperada = "30/09/2023";
        Date date = fechaHoy;
        date.setMonth(Calendar.SEPTEMBER);
        date.setYear(123); // 1900 + 123 = 2023
        date.setDate(30);
        String fechaObtenida = Util.getFechaFormato(date, "dd/MM/yyyy");
        assertEquals( fechaEsperada, fechaObtenida, "El formato no es el mismo");
    }

    @Test
    public void deberiaDevolverLaFechaDeHoy(){
        Date fechaObtenida = Util.getFechaHoy();
        assertThat(fechaHoy).isCloseTo(fechaObtenida, 900L);
    }

    @Test
    public void deberiaDevolverLaPrimeraHoraDeHoy(){
        Date fechaEsperada = fechaHoy;
        fechaEsperada.setSeconds(0);
        fechaEsperada.setMinutes(0);
        fechaEsperada.setHours(0);
        Date fechaObtenida = Util.getPrimeraHora(fechaHoy);
        assertThat(fechaEsperada).isCloseTo(fechaObtenida, 100L);
    }

    @Test
    public void deberiaDevolverLaUltimaHoraDeHoy(){
        Date fechaEsperada = Calendar.getInstance().getTime();
        fechaEsperada.setSeconds(59);
        fechaEsperada.setMinutes(59);
        fechaEsperada.setHours(23);
        Date fechaObtenida = Util.getUltimaHora(fechaHoy);
        assertThat(fechaEsperada).isCloseTo(fechaObtenida, 100L);
    }

    @Test
    public void deberiaCompararEntreFechas(){
        Date fechaAnterior = new Date(123, Calendar.SEPTEMBER, 29);  // 29/09/2023
        Date fechaPosterior = new Date(123, Calendar.OCTOBER, 1);    // 01/10/2023
        Date fechaAComparar = new Date(123, Calendar.SEPTEMBER, 30); // 30/09/2023

        assertTrue(Util.compararEntreFechas(fechaAnterior, fechaPosterior, fechaAComparar), "La fecha "
                + Util.getFechaFormato(fechaAComparar, "dd/MM/yyyy")
                + " no esta entre "
                + Util.getFechaFormato(fechaAnterior, "dd/MM/yyyy")
                + " y "
                + Util.getFechaFormato(fechaPosterior, "dd/MM/yyyy"));
    }

    @Test
    public void deberiaComprobarSiFechaEsAnteriorAOTra(){
        Date fechaAnterior = new Date(123, Calendar.SEPTEMBER, 29);  // 29/09/2023
        Date fechaAComparar = new Date(123, Calendar.SEPTEMBER, 30); // 30/09/2023
        assertTrue(Util.esFechaAnterior(fechaAnterior, fechaAComparar));
    }

    @Test
    public void deberiaComprobarSiFechaEsPosteriorAOtra(){
        Date fechaPosterior = new Date(123, Calendar.OCTOBER, 1);    // 01/10/2023
        Date fechaAComparar = new Date(123, Calendar.SEPTEMBER, 30); // 30/09/2023
        assertTrue(Util.esFechaPosterior(fechaPosterior, fechaAComparar));
    }
    @Test
    public void deberiaCambiarFechaPasadoPorParametro(){
        String fechaEsperada = "30/09/2023";

        Date fechaObtenida = Util.cambiarFecha(new Date(), 30, 9, 2023);
        String fechaObtenidaAux = Util.getFechaFormato(fechaObtenida, "dd/MM/yyyy");

        assertEquals(fechaEsperada, fechaObtenidaAux);
    }
}