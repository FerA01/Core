package org.core.utilidades.util.exception;

public class NoExisteCuentaBancariaException extends NullPointerException{
    public NoExisteCuentaBancariaException(String cbu){
        super("No existe la cuenta bancaria con cbu " + cbu);
    }
}
