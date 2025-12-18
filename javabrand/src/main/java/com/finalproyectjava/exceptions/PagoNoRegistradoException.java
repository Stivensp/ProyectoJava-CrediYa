package com.finalproyectjava.exceptions;

public class PagoNoRegistradoException extends RuntimeException {

    public PagoNoRegistradoException(String mensaje) {
        super(mensaje);
    }

    public PagoNoRegistradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
