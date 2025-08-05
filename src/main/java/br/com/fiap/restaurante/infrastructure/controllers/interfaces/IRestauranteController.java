package br.com.fiap.restaurante.infrastructure.controllers.interfaces;

import br.com.fiap.restaurante.infrastructure.dtos.PaginaDTO;
import br.com.fiap.restaurante.infrastructure.dtos.RestauranteDTO;
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
                    @ApiResponse(responseCode = "201", description = "Restaurante criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            }
    )
    RestauranteDTO criarRestaurante(@RequestBody @Validated final RestauranteDTO restauranteDTO);

    @Operation(
            summary = "Altera um restaurante existente",
            description = "Atualiza os dados do restaurante identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Restaurante alterado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
            }
    )
    RestauranteDTO alterarRestaurante(@RequestBody @Validated final RestauranteDTO restauranteDTO,
                                      @PathVariable final Long id);

    @Operation(
            summary = "Lista restaurantes",
            description = "Retorna uma lista paginada de restaurantes.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de restaurantes retornada com sucesso")
            }
    )
    List<RestauranteDTO> listarRestaurantes(final PaginaDTO paginaDTO);

    @Operation(
            summary = "Consulta restaurante por ID",
            description = "Retorna os dados do restaurante pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Restaurante encontrado"),
                    @ApiResponse(responseCode = "404", description = "Restaurantenão encontrado")
            }
    )
    RestauranteDTO consultarRestaurante(@PathVariable final Long id);

    @Operation(
            summary = "Remove restaurante",
            description = "Remove o restaurante identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Restaurante removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
            }
    )
    void removerRestaurante(@PathVariable final Long id);
}
