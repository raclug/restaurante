package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.infrastructure.dtos.ItemCardapioDTO;

public class ItemCardapioDTOMapper {

    private ItemCardapioDTOMapper() {
    }

    public static ItemCardapio toDomain(final ItemCardapioDTO itemCardapioDTO) {

        return ItemCardapio.builder()
                .id(itemCardapioDTO.getId())
                .nome(itemCardapioDTO.getNome())
                .descricao(itemCardapioDTO.getDescricao())
                .preco(itemCardapioDTO.getPreco())
                .disponivelApenasNoRestaurante(itemCardapioDTO.getDisponivelApenasNoRestaurante())
                .foto(itemCardapioDTO.getFoto())
                .build();

    }

    public static ItemCardapioDTO toDTO(final ItemCardapio itemCardapio) {

        return ItemCardapioDTO.builder()
                .id(itemCardapio.getId())
                .nome(itemCardapio.getNome())
                .descricao(itemCardapio.getDescricao())
                .preco(itemCardapio.getPreco())
                .disponivelApenasNoRestaurante(itemCardapio.getDisponivelApenasNoRestaurante())
                .foto(itemCardapio.getFoto())
                .build();

    }
}
