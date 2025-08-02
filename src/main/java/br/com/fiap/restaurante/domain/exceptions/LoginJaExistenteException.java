package br.com.fiap.restaurante.domain.exceptions;

public class LoginJaExistenteException extends RuntimeException {
    public LoginJaExistenteException(String message) {
        super(message);
    }
}
