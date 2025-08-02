package br.com.fiap.restaurante.infrastructure.controllers.interfaces;

import br.com.fiap.restaurante.dtos.LoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Login", description = "Operações de autenticação de usuários")
public interface ILoginController {


    @Operation(
            summary = "Valida o login do usuário",
            description = "Recebe as credenciais do usuário e valida o login.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login válido"),
                    @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida")
            }
    )
    void validarLogin(@RequestBody @Validated final LoginDTO loginDTO);
}
