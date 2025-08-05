package br.com.fiap.restaurante.infrastructure.controllers;


import br.com.fiap.restaurante.infrastructure.controllers.interfaces.IItemCardapioController;
import br.com.fiap.restaurante.infrastructure.dtos.PaginaDTO;
import br.com.fiap.restaurante.infrastructure.dtos.ItemCardapioDTO;
import br.com.fiap.restaurante.infrastructure.mappers.ItemCardapioDTOMapper;
import br.com.fiap.restaurante.infrastructure.services.ItemCardapioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fiap.restaurante.infrastructure.mappers.ItemCardapioDTOMapper.toDTO;
import static br.com.fiap.restaurante.infrastructure.mappers.ItemCardapioDTOMapper.toDomain;

@RestController
@RequestMapping("/v1/itens-cardapio")
@AllArgsConstructor
public class ItemCardapioController implements IItemCardapioController {

    private final ItemCardapioService itemCardapioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ItemCardapioDTO criarItemCardapio(@RequestBody @Validated final ItemCardapioDTO tipoUsuarioDTO) {
        return toDTO(itemCardapioService.criarItemCardapio(toDomain(tipoUsuarioDTO)));
    }

    @PutMapping("/{id}")
    public ItemCardapioDTO alterarItemCardapio(@RequestBody @Validated final ItemCardapioDTO tipoUsuarioDTO,
                                               @PathVariable final Long id) {

        return toDTO(itemCardapioService.alterarItemCardapio(id, toDomain(tipoUsuarioDTO)));
    }

    @GetMapping
    public List<ItemCardapioDTO> listarItensCardapio(final PaginaDTO paginaDTO) {
        return itemCardapioService.listarItensCardapio(paginaDTO.getPagina(), paginaDTO.getTamanhoPagina())
                .stream()
                .map(ItemCardapioDTOMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ItemCardapioDTO consultarItemCardapio(@PathVariable final Long id) {
        return toDTO(itemCardapioService.consultarItemCardapio(id));
    }

    @DeleteMapping("/{id}")
    public void removerItemCardapio(@PathVariable final Long id) {
        itemCardapioService.removerItemCardapio(id);
    }
}
