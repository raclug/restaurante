package br.com.fiap.restaurante.domain.exceptions;

public class NaoAutorizadoException extends RuntimeException {

    public NaoAutorizadoException(String message) {
        super(message);
    }
}
