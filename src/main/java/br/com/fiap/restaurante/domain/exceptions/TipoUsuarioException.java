package br.com.fiap.restaurante.domain.exceptions;

public class TipoUsuarioException extends RuntimeException {
    public TipoUsuarioException(String message) {
        super(message);
    }
}
