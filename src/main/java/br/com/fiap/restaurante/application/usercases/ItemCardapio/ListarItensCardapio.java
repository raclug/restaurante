package br.com.fiap.restaurante.application.usercases.ItemCardapio;

import br.com.fiap.restaurante.domain.entities.ItemCardapio;

import java.util.List;

public interface ListarItensCardapio {

    List<ItemCardapio> execute(Integer pagina, Integer tamanhoPagina);
}
