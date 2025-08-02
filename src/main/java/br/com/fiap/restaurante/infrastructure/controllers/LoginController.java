package br.com.fiap.restaurante.infrastructure.controllers;

import br.com.fiap.restaurante.dtos.LoginDTO;
import br.com.fiap.restaurante.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/usuarios/logins")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public void validarLogin(@RequestBody @Validated final LoginDTO loginDTO) {

        loginService.validarLogin(loginDTO);
    }
}
