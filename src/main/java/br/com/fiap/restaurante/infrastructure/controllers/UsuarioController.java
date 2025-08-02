package br.com.fiap.restaurante.infrastructure.controllers;

import br.com.fiap.restaurante.dtos.AlteracaoUsuarioDTO;
import br.com.fiap.restaurante.dtos.UsuarioDTO;
import br.com.fiap.restaurante.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
@AllArgsConstructor
@Tag(name = "Usuário", description = "Operações de gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(
            summary = "Cria um novo usuário",
            description = "Cadastra um novo usuário no sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            }
    )
    public UsuarioDTO criarUsuario(@RequestBody @Validated final UsuarioDTO usuarioDTO) {
        return usuarioService.criarUsuario(usuarioDTO);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Altera um usuário existente",
            description = "Atualiza os dados do usuário identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário alterado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    public UsuarioDTO alterarUsuario(@RequestBody @Validated final AlteracaoUsuarioDTO usuarioDTO,
                                     @PathVariable final Long id) {

        return usuarioService.alterarUsuario(id, usuarioDTO);
    }

    @GetMapping
    @Operation(
            summary = "Lista usuários",
            description = "Retorna uma lista paginada de usuários.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
            }
    )
    public List<UsuarioDTO> listarUsuarios(final Pageable pageable) {
        return usuarioService.listarUsuarios(pageable);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consulta usuário por ID",
            description = "Retorna os dados do usuário identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )

    public UsuarioDTO consultarUsuario(@PathVariable final Long id) {
        return usuarioService.consultarUsuario(id);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remove usuário",
            description = "Remove o usuário identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    public void removerUsuario(@PathVariable final Long id) {
        usuarioService.removerUsuario(id);
    }
}
