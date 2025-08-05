package br.com.fiap.restaurante.infrastructure.controllers;


import br.com.fiap.restaurante.application.usercases.senha.SalvarSenha;
import br.com.fiap.restaurante.infrastructure.controllers.interfaces.ISenhaController;
import br.com.fiap.restaurante.infrastructure.dtos.SenhaDTO;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuarios/{usuarioId}/senhas")
@AllArgsConstructor
public class SenhaController implements ISenhaController {

    private final SalvarSenha salvarSenha;

    @PutMapping
    public void alterarSenha(@RequestBody @Validated final SenhaDTO senhaDTO,
                             @PathVariable final Long usuarioId) {

        salvarSenha.execute(usuarioId, senhaDTO.getSenha(), senhaDTO.getNovaSenha());
    }
}
