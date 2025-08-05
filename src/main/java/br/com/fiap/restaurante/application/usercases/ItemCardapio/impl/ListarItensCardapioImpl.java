package br.com.fiap.restaurante.application.usercases.ItemCardapio.impl;

import br.com.fiap.restaurante.application.ports.ItemCardapioPort;
import br.com.fiap.restaurante.application.usercases.ItemCardapio.ListarItensCardapio;
import br.com.fiap.restaurante.domain.entities.ItemCardapio;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ListarItensCardapioImpl implements ListarItensCardapio {

    private final ItemCardapioPort itemCardapioPort;

    @Override
    public List<ItemCardapio> execute(final Integer pagina, final Integer tamanhoPagina) {

        return itemCardapioPort.listarItensCardapio(pagina, tamanhoPagina);
    }
}
