package br.com.fiap.restaurante.infrastructure.controllers;

import br.com.fiap.restaurante.dtos.SenhaDTO;
import br.com.fiap.restaurante.services.SenhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuarios/{id}/senhas")
@AllArgsConstructor
@Tag(name = "Senha", description = "Operações de alteração de senha de usuários")
public class SenhaController {

    private final SenhaService senhaService;

    @PutMapping
    @Operation(
            summary = "Altera a senha do usuário",
            description = "Recebe a nova senha e altera a senha do usuário identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    public void alterarSenha(@RequestBody @Validated final SenhaDTO senhaDTO,
                             @PathVariable final Long id) {

        senhaService.alterarSenha(id, senhaDTO);
    }
}
