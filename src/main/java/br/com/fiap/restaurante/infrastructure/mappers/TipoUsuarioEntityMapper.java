package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.TipoUsuario;
import br.com.fiap.restaurante.infrastructure.persistence.entities.TipoUsuarioEntity;

public class TipoUsuarioEntityMapper {

    private TipoUsuarioEntityMapper() {}

    public static TipoUsuarioEntity toEntity(final TipoUsuario tipoUsuario) {

        return TipoUsuarioEntity.builder()
            .id(tipoUsuario.getId())
            .nome(tipoUsuario.getNome())
            .build();
    }

    public static TipoUsuario toDomain(final TipoUsuarioEntity tipoUsuarioEntity) {

        return new TipoUsuario(
            tipoUsuarioEntity.getId(),
            tipoUsuarioEntity.getNome()
        );
    }
}
