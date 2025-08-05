package br.com.fiap.restaurante.domain.exceptions;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(final String message) {
        super(message);
    }
}
