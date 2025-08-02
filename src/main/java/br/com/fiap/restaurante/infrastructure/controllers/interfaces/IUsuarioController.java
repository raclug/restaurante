package br.com.fiap.restaurante.infrastructure.controllers.interfaces;

import br.com.fiap.restaurante.dtos.AlteracaoUsuarioDTO;
import br.com.fiap.restaurante.dtos.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuário", description = "Operações de gerenciamento de usuários")
public interface IUsuarioController {

    @Operation(
            summary = "Cria um novo usuário",
            description = "Cadastra um novo usuário no sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            }
    )
    UsuarioDTO criarUsuario(@RequestBody @Validated final UsuarioDTO usuarioDTO);

    @Operation(
            summary = "Altera um usuário existente",
            description = "Atualiza os dados do usuário identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário alterado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    UsuarioDTO alterarUsuario(@RequestBody @Validated final AlteracaoUsuarioDTO usuarioDTO,
                                     @PathVariable final Long id);

    @Operation(
            summary = "Lista usuários",
            description = "Retorna uma lista paginada de usuários.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
            }
    )
    List<UsuarioDTO> listarUsuarios(final Pageable pageable);

    @Operation(
            summary = "Consulta usuário por ID",
            description = "Retorna os dados do usuário identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )

    UsuarioDTO consultarUsuario(@PathVariable final Long id);

    @Operation(
            summary = "Remove usuário",
            description = "Remove o usuário identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    void removerUsuario(@PathVariable final Long id);
}
