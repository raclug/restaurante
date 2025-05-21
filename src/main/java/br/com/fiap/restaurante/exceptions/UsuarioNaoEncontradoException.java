package br.com.fiap.restaurante.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(final String message) {
        super(message);
    }
}
