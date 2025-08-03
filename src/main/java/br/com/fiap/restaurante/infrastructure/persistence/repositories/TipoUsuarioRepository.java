package br.com.fiap.restaurante.infrastructure.persistence.repositories;

import br.com.fiap.restaurante.infrastructure.persistence.entities.TipoUsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends CrudRepository<TipoUsuarioEntity, Long>,
        PagingAndSortingRepository<TipoUsuarioEntity, Long> {
}
