package br.com.fiap.restaurante.infrastructure.handlers;


import br.com.fiap.restaurante.domain.exceptions.LoginJaExistenteException;
import br.com.fiap.restaurante.domain.exceptions.NaoAutorizadoException;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import br.com.fiap.restaurante.domain.exceptions.TipoUsuarioException;
import br.com.fiap.restaurante.infrastructure.dtos.MensagemErroDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NaoAutorizadoException.class)
    public MensagemErroDTO handleUsuarioNaoAutorizadoException(NaoAutorizadoException ex) {
        return MensagemErroDTO.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .erros(List.of(ex.getMessage()))
                .build();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({RegistroNaoEncontradoException.class, TipoUsuarioException.class})
    public MensagemErroDTO handleNaoEncontradoException(RuntimeException ex) {
        return MensagemErroDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .erros(List.of(ex.getMessage()))
                .build();
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(LoginJaExistenteException.class)
    public MensagemErroDTO handleLoginJaExistenteException(LoginJaExistenteException ex) {
        return MensagemErroDTO.builder()
                .status(HttpStatus.CONFLICT.value())
                .erros(List.of(ex.getMessage()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MensagemErroDTO handleValidationException(MethodArgumentNotValidException ex) {
        var fieldErros = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();

        var globalErros = ex.getBindingResult().getGlobalErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        var erros = new java.util.ArrayList<String>();
        erros.addAll(fieldErros);
        erros.addAll(globalErros);

        return MensagemErroDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .erros(erros)
                .build();
    }
}
