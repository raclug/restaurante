package br.com.fiap.restaurante.infrastructure.persistence.repositories;

import br.com.fiap.restaurante.infrastructure.persistence.entities.ItemCardapioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCardapioRepository extends CrudRepository<ItemCardapioEntity, Long>,
        PagingAndSortingRepository<ItemCardapioEntity, Long> {
}
