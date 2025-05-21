package br.com.fiap.restaurante.handlers;

import br.com.fiap.restaurante.dtos.MensagemErroDTO;
import br.com.fiap.restaurante.exceptions.UsuarioNaoAutorizadoException;
import br.com.fiap.restaurante.exceptions.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UsuarioNaoAutorizadoException.class)
    public MensagemErroDTO handleUsuarioNaoAutorizadoException(UsuarioNaoAutorizadoException ex) {
        return MensagemErroDTO.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .mensagem(ex.getMessage())
                .build();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public MensagemErroDTO handleNaoEncontradoException(UsuarioNaoEncontradoException ex) {
        return MensagemErroDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .mensagem(ex.getMessage())
                .build();
    }


}
