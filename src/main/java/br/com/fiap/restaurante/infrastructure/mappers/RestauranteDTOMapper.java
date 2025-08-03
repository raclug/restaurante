package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.Restaurante;
import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.infrastructure.dtos.RestauranteDTO;

public class RestauranteDTOMapper {

    private RestauranteDTOMapper() {
    }

    public static Restaurante toDomain(final RestauranteDTO restauranteDTO) {

        return new Restaurante(restauranteDTO.getId(),
                restauranteDTO.getNome(),
                EnderecoDTOMapper.toDomain(restauranteDTO.getEndereco()),
                restauranteDTO.getTipoCozinha(),
                restauranteDTO.getHorarioAbertura(),
                restauranteDTO.getHorarioFechamento(),
                restauranteDTO.getResponsavelId());
    }

    public static RestauranteDTO toDTO(final Restaurante restaurante) {

        return RestauranteDTO.builder()
                .id(restaurante.getId())
                .nome(restaurante.getNome())
                .endereco(EnderecoDTOMapper.toDTO(restaurante.getEndereco()))
                .tipoCozinha(restaurante.getTipoCozinha())
                .horarioAbertura(restaurante.getHorarioAbertura())
                .horarioFechamento(restaurante.getHorarioFechamento())
                .responsavelId(restaurante.getIdResponsavel())
                .build();
    }
}
