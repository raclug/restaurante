package br.com.fiap.restaurante.repositories;

import br.com.fiap.restaurante.entities.SenhaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SenhaRepository extends CrudRepository<SenhaEntity, Long> {

    Optional<SenhaEntity> findByUsuarioId(Long usuarioId);
}
