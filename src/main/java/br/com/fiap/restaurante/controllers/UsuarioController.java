package br.com.fiap.restaurante.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }
}
