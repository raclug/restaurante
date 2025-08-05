package br.com.fiap.restaurante.application.usercases.restaurante.impl;

import br.com.fiap.restaurante.application.ports.RestaurantePort;
import br.com.fiap.restaurante.application.usercases.restaurante.RemoverRestaurante;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoverRestauranteImpl implements RemoverRestaurante {

    private final RestaurantePort restaurantePort;

    @Override
    public void execute(Long id) {
        var restaurante = restaurantePort.consultarRestaurantePorId(id);

        if (restaurante.isEmpty()) {
            throw new RegistroNaoEncontradoException("Restaurante não encontrado.");
        }

        restaurantePort.removerRestaurante(id);
    }
}
