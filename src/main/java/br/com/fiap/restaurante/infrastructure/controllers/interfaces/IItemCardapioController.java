package br.com.fiap.restaurante.infrastructure.controllers.interfaces;

import br.com.fiap.restaurante.infrastructure.dtos.ItemCardapioDTO;
import br.com.fiap.restaurante.infrastructure.dtos.PaginaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Item Cardápio", description = "Operações de gerenciamento de itens do cardápio")
public interface IItemCardapioController {

    @Operation(
            summary = "Cria um novo item do cardápio",
            description = "Cadastra um novo item do cardápio no sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Item do Cardápio criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            }
    )
    ItemCardapioDTO criarItemCardapio(@RequestBody @Validated final ItemCardapioDTO itemCardapioDTO);

    @Operation(
            summary = "Altera um item do cardápio existente",
            description = "Atualiza os dados do item do cardápio identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Item do Cardápio alterado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "404", description = "Item do Cardápio não encontrado")
            }
    )
    ItemCardapioDTO alterarItemCardapio(@RequestBody @Validated final ItemCardapioDTO itemCardapioDTO,
                                        @PathVariable final Long id);

    @Operation(
            summary = "Lista itens do cardápio",
            description = "Retorna uma lista paginada de itens do cardápio.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de itens do cardápio retornada com sucesso")
            }
    )
    List<ItemCardapioDTO> listarItensCardapio(final PaginaDTO paginaDTO);

    @Operation(
            summary = "Consulta itens do cardápio por ID",
            description = "Retorna os dados do item do cardápio identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Item do Cardápio encontrado"),
                    @ApiResponse(responseCode = "404", description = "Item do Cardápio não encontrado")
            }
    )
    ItemCardapioDTO consultarItemCardapio(@PathVariable final Long id);

    @Operation(
            summary = "Remove item do cardápio",
            description = "Remove o item do cardápio identificado pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Item do Cardápio removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Item do Cardápio não encontrado")
            }
    )
    void removerItemCardapio(@PathVariable final Long id);
}
