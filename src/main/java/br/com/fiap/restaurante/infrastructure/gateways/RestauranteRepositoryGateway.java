package br.com.fiap.restaurante.infrastructure.gateways;

import br.com.fiap.restaurante.application.ports.RestaurantePort;
import br.com.fiap.restaurante.domain.entities.Restaurante;
import br.com.fiap.restaurante.infrastructure.mappers.RestauranteEntityMapper;
import br.com.fiap.restaurante.infrastructure.persistence.repositories.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.fiap.restaurante.infrastructure.mappers.PageableMapper.toPageable;
import static br.com.fiap.restaurante.infrastructure.mappers.RestauranteEntityMapper.toDomain;
import static br.com.fiap.restaurante.infrastructure.mappers.RestauranteEntityMapper.toEntity;

@Component
@AllArgsConstructor
public class RestauranteRepositoryGateway implements RestaurantePort {

    private final RestauranteRepository restauranteRepository;

    @Override
    public Restaurante salvarRestaurante(final Restaurante restaurante) {

        final var restauranteEntity = restauranteRepository.save(toEntity(restaurante));

        return toDomain(restauranteEntity);
    }

    @Override
    public void removerRestaurante(Long id) {
        restauranteRepository.deleteAllById(Collections.singletonList(id));
    }

    @Override
    public List<Restaurante> listarRestaurante(Integer pagina, Integer tamanhoPagina) {

        var pageable = toPageable(pagina, tamanhoPagina);

        return restauranteRepository.findAll(pageable)
                .stream()
                .map(RestauranteEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Restaurante> consultarRestaurantePorId(Long id) {
        return restauranteRepository.findById(id).map(RestauranteEntityMapper::toDomain);
    }
}
