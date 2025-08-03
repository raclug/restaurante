package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.TipoUsuario;
import br.com.fiap.restaurante.infrastructure.dtos.TipoUsuarioDTO;

public class TipoUsuarioDTOMapper {

    private TipoUsuarioDTOMapper() {
    }

    public static TipoUsuario toDomain(final TipoUsuarioDTO tipoUsuarioDTO) {

        return new TipoUsuario(
                tipoUsuarioDTO.getId(),
                tipoUsuarioDTO.getNome()
        );
    }

    public static TipoUsuarioDTO toDTO(final TipoUsuario tipoUsuario) {

        return TipoUsuarioDTO.builder()
                .id(tipoUsuario.getId())
                .nome(tipoUsuario.getNome())
                .build();
    }
}
