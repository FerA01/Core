package org.core.utilidades.util.exception;

public class NumeroFueraDeRangoException extends Exception{
    public NumeroFueraDeRangoException(int rangoMinimo, int rangoMaximo, int numero){
        super("El número " + numero + " se encuentra fuera del rango " + " {" + rangoMinimo + ":" + rangoMaximo  + "}.");
    }
}
