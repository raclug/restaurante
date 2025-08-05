package br.com.fiap.restaurante.application.usercases.restaurante.impl;

import br.com.fiap.restaurante.application.ports.RestaurantePort;
import br.com.fiap.restaurante.application.usercases.restaurante.ConsultarRestaurante;
import br.com.fiap.restaurante.domain.entities.Restaurante;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConsultarRestauranteImpl implements ConsultarRestaurante {

    private final RestaurantePort restaurantePort;

    @Override
    public Restaurante execute(final Long id) {
        var restauranteConsulta = restaurantePort.consultarRestaurantePorId(id);

        if (restauranteConsulta.isEmpty()) {
            throw new RegistroNaoEncontradoException("Restaurante não encontrado.");
        }

        return restauranteConsulta.get();
    }
}
