package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.infrastructure.dtos.UsuarioDTO;

public class UsuarioDTOMapper {

    private UsuarioDTOMapper() {
    }

    public static Usuario toDomain(final UsuarioDTO usuarioDTO) {

        var endereco = EnderecoDTOMapper.toDomain(usuarioDTO.getEndereco());

        return new Usuario(
                usuarioDTO.getId(),
                usuarioDTO.getNome(),
                usuarioDTO.getEmail(),
                usuarioDTO.getLogin(),
                usuarioDTO.getSenha(),
                usuarioDTO.getTipoUsuario(),
                endereco
        );
    }

    public static UsuarioDTO toDTO(final Usuario usuario) {

        var enderecoDTO = EnderecoDTOMapper.toDTO(usuario.endereco());

        return UsuarioDTO.builder()
                .id(usuario.id())
                .nome(usuario.nome())
                .email(usuario.email())
                .login(usuario.login())
                .tipoUsuario(usuario.tipoUsuario())
                .endereco(enderecoDTO).build();
    }
}
