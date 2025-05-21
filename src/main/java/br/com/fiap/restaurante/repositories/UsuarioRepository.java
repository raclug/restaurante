package br.com.fiap.restaurante.repositories;

import br.com.fiap.restaurante.entities.UsuarioEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends ListCrudRepository<UsuarioEntity, Long> {


    Optional<UsuarioEntity> findFirstByLogin(final String login);
}
