package br.com.fiap.restaurante.application.usercases.restaurante.impl;

import br.com.fiap.restaurante.application.ports.RestaurantePort;
import br.com.fiap.restaurante.application.usercases.restaurante.ListarRestaurante;
import br.com.fiap.restaurante.domain.entities.Restaurante;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ListarRestauranteImpl implements ListarRestaurante {

    private final RestaurantePort restaurantePort;

    @Override
    public List<Restaurante> execute(final Integer pagina, final Integer tamanhoPagina) {

        return restaurantePort.listarRestaurante(pagina, tamanhoPagina);
    }
}
