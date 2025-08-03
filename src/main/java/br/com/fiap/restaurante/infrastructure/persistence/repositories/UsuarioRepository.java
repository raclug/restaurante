package br.com.fiap.restaurante.infrastructure.persistence.repositories;

import br.com.fiap.restaurante.infrastructure.persistence.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends ListCrudRepository<UsuarioEntity, Long>,
        PagingAndSortingRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findFirstByLogin(final String login);

    @Modifying
    @Query("UPDATE UsuarioEntity u SET u.tipoUsuario.id = :idTipoUsuario WHERE u.id = :idUsuario")
    void addTipoUsuario(Long idUsuario, Long idTipoUsuario);
}
