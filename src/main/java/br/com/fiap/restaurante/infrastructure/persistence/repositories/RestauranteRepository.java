package br.com.fiap.restaurante.infrastructure.persistence.repositories;

import br.com.fiap.restaurante.infrastructure.persistence.entities.RestauranteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends CrudRepository<RestauranteEntity, Long>,
        PagingAndSortingRepository<RestauranteEntity, Long> {
}
