package br.com.fiap.restaurante.repositories;

import br.com.fiap.restaurante.entities.SenhaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SenhaRepository extends CrudRepository<SenhaEntity, Long> {

}
