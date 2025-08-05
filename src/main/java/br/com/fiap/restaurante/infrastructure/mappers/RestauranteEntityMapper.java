package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.Restaurante;
import br.com.fiap.restaurante.infrastructure.persistence.entities.RestauranteEntity;
import br.com.fiap.restaurante.infrastructure.persistence.entities.UsuarioEntity;

public class RestauranteEntityMapper {

    private RestauranteEntityMapper() {
    }

    public static RestauranteEntity toEntity(final Restaurante restaurante) {

        return new RestauranteEntity(
                restaurante.getId(),
                restaurante.getNome(),
                EnderecoEntityMapper.toEntity(restaurante.getEndereco()),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioAbertura(),
                restaurante.getHorarioFechamento(),
                UsuarioEntity.builder().id(restaurante.getIdResponsavel()).build()
        );
    }

    public static Restaurante toDomain(final RestauranteEntity restauranteEntity) {

        return new Restaurante(
                restauranteEntity.getId(),
                restauranteEntity.getNome(),
                EnderecoEntityMapper.toDomain(restauranteEntity.getEndereco()),
                restauranteEntity.getTipoCozinha(),
                restauranteEntity.getHorarioAbertura(),
                restauranteEntity.getHorarioFechamento(),
                restauranteEntity.getResponsavel().getId()
        );
    }
}
