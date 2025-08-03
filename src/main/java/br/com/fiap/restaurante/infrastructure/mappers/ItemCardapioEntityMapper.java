package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.infrastructure.persistence.entities.ItemCardapioEntity;

public class ItemCardapioEntityMapper {

    private ItemCardapioEntityMapper() {
    }

    public static ItemCardapioEntity toEntity(final ItemCardapio itemCardapio) {
        return new ItemCardapioEntity(
                itemCardapio.getId(),
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getDisponivelApenasNoRestaurante(),
                itemCardapio.getFoto()
        );
    }

    public static ItemCardapio toDomain(final ItemCardapioEntity itemCardapioEntity) {

        return new ItemCardapio(
                itemCardapioEntity.getId(),
                itemCardapioEntity.getNome(),
                itemCardapioEntity.getDescricao(),
                itemCardapioEntity.getPreco(),
                itemCardapioEntity.getDisponivelApenasNoRestaurante(),
                itemCardapioEntity.getFoto()
        );
    }
}
