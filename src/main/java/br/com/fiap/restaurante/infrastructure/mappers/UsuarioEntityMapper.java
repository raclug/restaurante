package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.infrastructure.persistence.entities.SenhaEntity;
import br.com.fiap.restaurante.infrastructure.persistence.entities.UsuarioEntity;

public class UsuarioEntityMapper {

    private UsuarioEntityMapper() {}

    public static UsuarioEntity toEntity(final Usuario usuario) {

        final var senhaEntity = SenhaEntity.builder().senha(usuario.senha()).build();

        final var enderecoEntity = EnderecoEntityMapper.toEntity(usuario.endereco());

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

        final var endereco = EnderecoEntityMapper.toDomain(usuarioEntity.getEndereco());

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
