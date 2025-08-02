package br.com.fiap.restaurante.domain.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(final String message) {
        super(message);
    }
}
