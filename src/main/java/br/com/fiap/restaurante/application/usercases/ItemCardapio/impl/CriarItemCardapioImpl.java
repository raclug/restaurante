package br.com.fiap.restaurante.application.usercases.ItemCardapio.impl;

import br.com.fiap.restaurante.application.ports.ItemCardapioPort;
import br.com.fiap.restaurante.application.usercases.ItemCardapio.CriarItemCardapio;
import br.com.fiap.restaurante.domain.entities.ItemCardapio;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriarItemCardapioImpl implements CriarItemCardapio {

    private final ItemCardapioPort itemCardapioPort;

    @Override
    public ItemCardapio execute(final ItemCardapio itemCardapio) {

        return itemCardapioPort.salvarItemCardapio(itemCardapio);
    }
}
