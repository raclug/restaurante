package br.com.fiap.restaurante.exceptions;

public class LoginJaExistenteException extends RuntimeException {
    public LoginJaExistenteException(String message) {
        super(message);
    }
}
