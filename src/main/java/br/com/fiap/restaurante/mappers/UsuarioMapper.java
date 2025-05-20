package br.com.fiap.restaurante.mappers;

import br.com.fiap.restaurante.dtos.UsuarioDTO;
import br.com.fiap.restaurante.entities.UsuarioEntity;

import static br.com.fiap.restaurante.mappers.EnderecoMapper.mapToEnderecoDTO;

public class UsuarioMapper {

    private UsuarioMapper() {
    }

    public static UsuarioDTO mapToUsuarioDTO(final UsuarioEntity usuarioEntity) {

        var enderecoEntity = usuarioEntity.getEndereco();

        var enderecoDTO = mapToEnderecoDTO(enderecoEntity);

        return UsuarioDTO.builder().id(usuarioEntity.getId())
                .nome(usuarioEntity.getNome())
                .email(usuarioEntity.getEmail())
                .login(usuarioEntity.getLogin())
                .endereco(enderecoDTO)
                .tipoUsuario(usuarioEntity.getTipoUsuario()).build();
    }
}
