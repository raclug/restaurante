package br.com.fiap.restaurante.application.usercases.restaurante;

import br.com.fiap.restaurante.domain.entities.Restaurante;

import java.util.List;

public interface ListarRestaurante {

    List<Restaurante> execute(Integer pagina, Integer tamanhoPagina);
}
