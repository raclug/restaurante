package br.com.fiap.restaurante.controllers;

import br.com.fiap.restaurante.dtos.LoginDTO;
import br.com.fiap.restaurante.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/usuarios/logins")
@AllArgsConstructor
@Tag(name = "Login", description = "Operações de autenticação de usuários")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    @Operation(
            summary = "Valida o login do usuário",
            description = "Recebe as credenciais do usuário e valida o login.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login válido"),
                    @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida")
            }
    )
    public void validarLogin(@RequestBody @Validated final LoginDTO loginDTO) {

        loginService.validarLogin(loginDTO);
    }
}
