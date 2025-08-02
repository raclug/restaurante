package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.infrastructure.persistence.entities.SenhaEntity;

public class SenhaEntityMapper {

    public static SenhaEntity toEntity(final String senha) {
        return SenhaEntity.builder().senha(senha).build();
    }
}
