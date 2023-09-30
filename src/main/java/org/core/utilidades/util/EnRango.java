package org.core.utilidades.util;

import org.core.utilidades.util.exception.NumeroFueraDeRangoException;

public class EnRango{
    private int rangoMinimo;
    private int rangoMaximo;

    public EnRango(){
        setRangoMinimo(0);
        setRangoMaximo(10);
    }

    public EnRango(int rangoMinimo, int rangoMaximo){
        setRangoMinimo(rangoMinimo);
        setRangoMaximo(rangoMaximo);
    }

    public boolean enRango(int numero) throws NumeroFueraDeRangoException {
        if (numero < getRangoMinimo() || numero > getRangoMaximo()){
            throw  new NumeroFueraDeRangoException(getRangoMinimo(), getRangoMaximo(), numero);
        }
        return true;
    }
    public int getRangoMinimo() { return rangoMinimo; }
    private void setRangoMinimo(int rangoMinimo) { this.rangoMinimo = rangoMinimo; }
    public int getRangoMaximo() { return rangoMaximo; }
    private void setRangoMaximo(int rangoMaximo) { this.rangoMaximo = rangoMaximo; }
}
