package br.com.fiap.restaurante.application.usercases.ItemCardapio;

import br.com.fiap.restaurante.domain.entities.ItemCardapio;

public interface AlterarItemCardapio {

    ItemCardapio execute(Long id, ItemCardapio itemCardapio);
}
