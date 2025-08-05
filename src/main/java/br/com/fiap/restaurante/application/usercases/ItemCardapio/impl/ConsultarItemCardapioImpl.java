package br.com.fiap.restaurante.application.usercases.ItemCardapio.impl;

import br.com.fiap.restaurante.application.ports.ItemCardapioPort;
import br.com.fiap.restaurante.application.usercases.ItemCardapio.ConsultarItemCardapio;
import br.com.fiap.restaurante.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConsultarItemCardapioImpl implements ConsultarItemCardapio {

    private final ItemCardapioPort itemCardapioPort;

    @Override
    public ItemCardapio execute(final Long id) {
        var itemCardapioConsulta = itemCardapioPort.consultarItemCardapioPorId(id);

        if (itemCardapioConsulta.isEmpty()) {
            throw new RegistroNaoEncontradoException("Item cardápio não encontrado.");
        }

        return itemCardapioConsulta.get();
    }
}
