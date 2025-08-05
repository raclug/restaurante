package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.infrastructure.persistence.entities.UsuarioEntity;

public class UsuarioEntityMapper {

    private UsuarioEntityMapper() {
    }

    public static UsuarioEntity toEntity(final Usuario usuario) {

        final var senhaEntity = SenhaEntityMapper.toEntity(usuario.getSenha());

        final var enderecoEntity = EnderecoEntityMapper.toEntity(usuario.getEndereco());

        final var tipoUsuarioEntity = TipoUsuarioEntityMapper.toEntity(usuario.getTipoUsuario());

        return UsuarioEntity.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .login(usuario.getLogin())
                .senha(senhaEntity)
                .endereco(enderecoEntity)
                .tipoUsuario(tipoUsuarioEntity)
                .build();
    }

    public static UsuarioEntity toEntityForUpdate(final UsuarioEntity usuarioAtual, final Usuario usuario) {

        final var enderecoEntity = EnderecoEntityMapper.toEntity(usuario.getEndereco());

        final var tipoUsuarioEntity = TipoUsuarioEntityMapper.toEntity(usuario.getTipoUsuario());

        return UsuarioEntity.builder()
                .id(usuarioAtual.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .login(usuarioAtual.getLogin())
                .senha(usuarioAtual.getSenha())
                .endereco(enderecoEntity)
                .tipoUsuario(tipoUsuarioEntity)
                .build();
    }

    public static Usuario toDomain(final UsuarioEntity usuarioEntity) {

        usuarioEntity.getSenha().setUsuario(usuarioEntity);

        final var senha = SenhaEntityMapper.toDomain(usuarioEntity.getSenha());

        final var endereco = EnderecoEntityMapper.toDomain(usuarioEntity.getEndereco());

        final var tipoUsuario = TipoUsuarioEntityMapper.toDomain(usuarioEntity.getTipoUsuario());

        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getLogin(),
                senha,
                tipoUsuario,
                endereco
        );
    }
}
