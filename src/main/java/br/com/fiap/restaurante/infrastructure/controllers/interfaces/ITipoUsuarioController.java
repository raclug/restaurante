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

@Tag(name = "Tipo Usuário", description = "Operações de gerenciamento de tipos de usuário")
public interface ITipoUsuarioController {

    @Operation(
            summary = "Cria um novo tipo de usuário",
            description = "Cadastra um novo tipo de usuário no sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tipo de Usuário criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            }
    )
    TipoUsuarioDTO criarTipoUsuario(@RequestBody @Validated final TipoUsuarioDTO tipoUsuarioDTO);

    @Operation(
            summary = "Altera um tipo de usuário existente",
            description = "Atualiza os dados do tipo de usuário identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tipo de usuário alterado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado")
            }
    )
    TipoUsuarioDTO alterarTipoUsuario(@RequestBody @Validated final TipoUsuarioDTO tipoUsuarioDTO,
                                     @PathVariable final Long id);

    @Operation(
            summary = "Lista tipos de usuário",
            description = "Retorna uma lista paginada de tipos de usuário.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de tipos de usuário retornada com sucesso")
            }
    )
    List<TipoUsuarioDTO> listarTiposUsuario(final PaginaDTO paginaDTO);

    @Operation(
            summary = "Consulta tipos de usuário por ID",
            description = "Retorna os dados do tipo de usuário identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tipo de Usuário encontrado"),
                    @ApiResponse(responseCode = "404", description = "Tipo de Usuário não encontrado")
            }
    )
    TipoUsuarioDTO consultarTipoUsuario(@PathVariable final Long id);

    @Operation(
            summary = "Remove tipo de usuário",
            description = "Remove o tipo de usuário identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Tipo de Usuário removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Tipo de Usuário não encontrado")
            }
    )
    void removerTipoUsuario(@PathVariable final Long id);
}
