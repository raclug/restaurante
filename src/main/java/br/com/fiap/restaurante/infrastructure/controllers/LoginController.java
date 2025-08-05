package br.com.fiap.restaurante.infrastructure.controllers;

import br.com.fiap.restaurante.application.usercases.login.EfetuarLogin;
import br.com.fiap.restaurante.infrastructure.controllers.interfaces.ILoginController;
import br.com.fiap.restaurante.infrastructure.dtos.LoginDTO;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.fiap.restaurante.infrastructure.mappers.LoginDTOMapper.toDomain;

@RestController
@RequestMapping("/v1/usuarios/logins")
@AllArgsConstructor
public class LoginController implements ILoginController {

    private final EfetuarLogin efetuarLogin;

    @PostMapping
    public void efetuarLogin(@RequestBody @Validated final LoginDTO loginDTO) {
        efetuarLogin.execute(toDomain(loginDTO));
    }
}
