package br.com.fiap.restaurante.infrastructure.controllers;

import br.com.fiap.restaurante.dtos.SenhaDTO;
import br.com.fiap.restaurante.infrastructure.controllers.interfaces.ISenhaController;
import br.com.fiap.restaurante.services.SenhaService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuarios/{id}/senhas")
@AllArgsConstructor
public class SenhaController implements ISenhaController {

    private final SenhaService senhaService;

    @PutMapping
    public void alterarSenha(@RequestBody @Validated final SenhaDTO senhaDTO,
                             @PathVariable final Long id) {

        senhaService.alterarSenha(id, senhaDTO);
    }
}
