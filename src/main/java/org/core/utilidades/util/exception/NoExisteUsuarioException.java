package org.core.utilidades.util.exception;

public class NoExisteUsuarioException extends NullPointerException{
    public NoExisteUsuarioException(String usuario){
        super("No existe el usuario " + usuario);
    }
}
