package br.com.fiap.restaurante.infrastructure.controllers.interfaces;

import br.com.fiap.restaurante.infrastructure.dtos.PaginaDTO;
import br.com.fiap.restaurante.infrastructure.dtos.TipoUsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Restaurante", description = "Operações de gerenciamento de restaurantes")
public interface IRestauranteController {

    @Operation(
            summary = "Cria um novo restaurante",
            description = "Cadastra um novo restaurante no sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tipo de Usuário criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            }
    )
    TipoUsuarioDTO criarTipoUsuario(@RequestBody @Validated final TipoUsuarioDTO tipoUsuarioDTO);

    @Operation(
            summary = "Altera um restaurante existente",
            description = "Atualiza os dados do restaurante identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tipo de usuário alterado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado")
            }
    )
    TipoUsuarioDTO alterarTipoUsuario(@RequestBody @Validated final TipoUsuarioDTO tipoUsuarioDTO,
                                     @PathVariable final Long id);

    @Operation(
            summary = "Lista restaurantes",
            description = "Retorna uma lista paginada de restaurantes.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de tipos de usuário retornada com sucesso")
            }
    )
    List<TipoUsuarioDTO> listarTiposUsuario(final PaginaDTO paginaDTO);

    @Operation(
            summary = "Consulta restaurante por ID",
            description = "Retorna os dados do restaurante pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tipo de Usuário encontrado"),
                    @ApiResponse(responseCode = "404", description = "Tipo de Usuário não encontrado")
            }
    )
    TipoUsuarioDTO consultarTipoUsuario(@PathVariable final Long id);

    @Operation(
            summary = "Remove restaurante",
            description = "Remove o restaurante identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Tipo de Usuário removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Tipo de Usuário não encontrado")
            }
    )
    void removerTipoUsuario(@PathVariable final Long id);
}
