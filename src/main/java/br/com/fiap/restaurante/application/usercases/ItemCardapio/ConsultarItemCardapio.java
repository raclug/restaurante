package br.com.fiap.restaurante.application.usercases.ItemCardapio;

import br.com.fiap.restaurante.domain.entities.ItemCardapio;

public interface ConsultarItemCardapio {

    ItemCardapio execute(Long id);
}
