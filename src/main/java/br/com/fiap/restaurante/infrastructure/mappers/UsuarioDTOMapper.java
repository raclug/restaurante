package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.Senha;
import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.infrastructure.dtos.UsuarioDTO;

public class UsuarioDTOMapper {

    private UsuarioDTOMapper() {
    }

    public static Usuario toDomain(final UsuarioDTO usuarioDTO) {

        var endereco = EnderecoDTOMapper.toDomain(usuarioDTO.getEndereco());

        var senha = new Senha(null, null, usuarioDTO.getSenha());

        return new Usuario(
                usuarioDTO.getId(),
                usuarioDTO.getNome(),
                usuarioDTO.getEmail(),
                usuarioDTO.getLogin(),
                senha,
                usuarioDTO.getTipoUsuario(),
                endereco
        );
    }

    public static UsuarioDTO toDTO(final Usuario usuario) {

        var enderecoDTO = EnderecoDTOMapper.toDTO(usuario.getEndereco());

        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .login(usuario.getLogin())
                .tipoUsuario(usuario.getTipoUsuario())
                .endereco(enderecoDTO).build();
    }
}
