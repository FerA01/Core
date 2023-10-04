package org.core.utilidades.util;

import org.core.utilidades.util.exception.NumeroFueraDeRangoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnRangoTest {
    private int numero;

    @Test
    public void deberiaEstarDentroRango() throws NumeroFueraDeRangoException {
        int rangoMinimo = 1;
        int rangoMaximo = 10;
        setNumero(5);
        EnRango rango = new EnRango(rangoMinimo, rangoMaximo);

        assertTrue(rango.enRango(getNumero()));
    }

    @Test
    public void deberiaEstarFueraRango(){
        int rangoMinimo = 1;
        int rangoMaximo = 10;
        setNumero(100);
        EnRango rango = new EnRango(rangoMinimo, rangoMaximo);
        Throwable exception = assertThrows(NumeroFueraDeRangoException.class, () -> rango.enRango(getNumero()));

        String mensajeEsperado = "El número " + getNumero() + " se encuentra fuera del rango " + " {" + rangoMinimo + ":" + rangoMaximo  + "}.";
        String mensajeObtenido = exception.getMessage();
        assertEquals(mensajeEsperado, mensajeObtenido, "No se obtuvo la excepcion esperada.");
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
}