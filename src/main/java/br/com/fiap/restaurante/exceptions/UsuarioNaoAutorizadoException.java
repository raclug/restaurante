package br.com.fiap.restaurante.exceptions;

public class UsuarioNaoAutorizadoException extends RuntimeException {

    public UsuarioNaoAutorizadoException(String message) {
        super(message);
    }
}
