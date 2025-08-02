package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.infrastructure.dtos.AlteracaoUsuarioDTO;

public class AlteracaoUsuarioDTOMapper {

    private AlteracaoUsuarioDTOMapper() {
    }

    public static Usuario toDomain(final AlteracaoUsuarioDTO usuarioDTO) {

        var endereco = EnderecoDTOMapper.toDomain(usuarioDTO.getEndereco());

        return new Usuario(
                usuarioDTO.getId(),
                usuarioDTO.getNome(),
                usuarioDTO.getEmail(),
                null,
                null,
                usuarioDTO.getTipoUsuario(),
                endereco
        );
    }
}
