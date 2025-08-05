package br.com.fiap.restaurante.application.ports;

import br.com.fiap.restaurante.domain.entities.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestaurantePort {

    Restaurante salvarRestaurante(Restaurante restaurante);

    void removerRestaurante(Long id);

    Optional<Restaurante> consultarRestaurantePorId(Long id);

    List<Restaurante> listarRestaurante(Integer pagina, Integer tamanhoPagina);
}
