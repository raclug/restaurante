package br.com.fiap.restaurante.repositories;

import br.com.fiap.restaurante.entities.UsuarioEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends ListCrudRepository<UsuarioEntity, Long> {
}
