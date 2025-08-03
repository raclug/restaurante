package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.TipoUsuario;
import br.com.fiap.restaurante.infrastructure.dtos.TipoUsuarioDTO;

public class TipoUsuarioDTOMapper {

    private TipoUsuarioDTOMapper() {
    }

    public static TipoUsuario toDomain(final TipoUsuarioDTO tipoUsuarioDTO) {

        if (tipoUsuarioDTO == null) {
            return null;
        }

        return new TipoUsuario(
                tipoUsuarioDTO.getId(),
                tipoUsuarioDTO.getNome()
        );
    }

    public static TipoUsuarioDTO toDTO(final TipoUsuario tipoUsuario) {

        if (tipoUsuario == null) {
            return null;
        }

        return TipoUsuarioDTO.builder()
                .id(tipoUsuario.getId())
                .nome(tipoUsuario.getNome())
                .build();
    }
}
