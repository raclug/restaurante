package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.application.ports.PasswordEncoderPort;
import br.com.fiap.restaurante.domain.entities.Senha;
import br.com.fiap.restaurante.infrastructure.persistence.entities.SenhaEntity;

public class SenhaEntityMapper {

    private SenhaEntityMapper() {
    }

    public static SenhaEntity toEntity(final Senha senha) {
        return SenhaEntity.builder().id(senha.getId()).senha(senha.getSenha()).build();
    }

    public static Senha toDomain(final SenhaEntity senhaEntity) {
        return new Senha(
                senhaEntity.getId(),
                senhaEntity.getUsuario().getId(),
                senhaEntity.getSenha()
        );
    }
}
