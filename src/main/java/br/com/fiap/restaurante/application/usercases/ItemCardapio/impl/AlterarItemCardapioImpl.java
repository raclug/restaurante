package br.com.fiap.restaurante.application.usercases.ItemCardapio.impl;

import br.com.fiap.restaurante.application.ports.ItemCardapioPort;
import br.com.fiap.restaurante.application.usercases.ItemCardapio.AlterarItemCardapio;
import br.com.fiap.restaurante.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlterarItemCardapioImpl implements AlterarItemCardapio {

    private final ItemCardapioPort itemCardapioPort;

    @Override
    public ItemCardapio execute(final Long id, final ItemCardapio itemCardapio) {

        var itemCardapioConsulta = itemCardapioPort.consultarItemCardapioPorId(id);

        if (itemCardapioConsulta.isEmpty()) {
            throw new RegistroNaoEncontradoException("Item cardápio não encontrado.");
        }

        var itemCardapioParaAlteracao = getItemCardapio(id, itemCardapio);

        return itemCardapioPort.salvarItemCardapio(itemCardapioParaAlteracao);
    }


    private ItemCardapio getItemCardapio(final Long id,
                                         final ItemCardapio itemCardapio) {

        return new ItemCardapio(
                id,
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getDisponivelApenasNoRestaurante(),
                itemCardapio.getFoto()
        );
    }
}
