package br.com.fiap.restaurante.application.usercases.ItemCardapio.impl;

import br.com.fiap.restaurante.application.ports.ItemCardapioPort;
import br.com.fiap.restaurante.application.usercases.ItemCardapio.RemoverItemCardapio;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoverItemCardapioImpl implements RemoverItemCardapio {

    private final ItemCardapioPort itemCardapioPort;

    @Override
    public void execute(Long id) {
        var itemCardapio = itemCardapioPort.consultarItemCardapioPorId(id);

        if (itemCardapio.isEmpty()) {
            throw new RegistroNaoEncontradoException("Item cardápio não encontrado.");
        }

        itemCardapioPort.removerItemCardapio(id);
    }
}
