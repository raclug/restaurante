package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.infrastructure.persistence.entities.UsuarioEntity;

public class UsuarioEntityMapper {

    public static UsuarioEntity toEntity(final Usuario usuario) {

        final var senhaEntity = SenhaEntityMapper.toEntity(usuario.senha());

        final var enderecoEntity = EnderecoMapper.toEntity(usuario.endereco());

        return UsuarioEntity.builder()
                .id(usuario.id())
                .nome(usuario.nome())
                .email(usuario.email())
                .login(usuario.login())
                .senha(senhaEntity)
                .endereco(enderecoEntity)
                .tipoUsuario(usuario.tipoUsuario())
                .build();
    }

    public static Usuario toDomain(final UsuarioEntity usuarioEntity) {

        final var senha = usuarioEntity.getSenha().getSenha();

        final var endereco = EnderecoMapper.toDomain(usuarioEntity.getEndereco());

        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getLogin(),
                senha,
                usuarioEntity.getTipoUsuario(),
                endereco
        );
    }
}
