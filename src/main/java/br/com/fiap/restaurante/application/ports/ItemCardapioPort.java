package br.com.fiap.restaurante.application.ports;

import br.com.fiap.restaurante.domain.entities.ItemCardapio;

import java.util.List;
import java.util.Optional;

public interface ItemCardapioPort {

    ItemCardapio salvarItemCardapio(ItemCardapio itemCardapio);

    void removerItemCardapio(Long id);

    Optional<ItemCardapio> consultarItemCardapioPorId(Long id);

    List<ItemCardapio> listarItensCardapio(Integer pagina, Integer tamanhoPagina);
}
