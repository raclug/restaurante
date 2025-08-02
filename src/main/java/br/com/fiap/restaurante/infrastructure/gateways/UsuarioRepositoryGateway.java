package br.com.fiap.restaurante.infrastructure.gateways;

import br.com.fiap.restaurante.application.gateways.UsuarioGateway;
import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.infrastructure.mappers.UsuarioEntityMapper;
import br.com.fiap.restaurante.infrastructure.persistence.entities.UsuarioEntity;
import br.com.fiap.restaurante.infrastructure.persistence.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import static br.com.fiap.restaurante.infrastructure.mappers.PageableMapper.toPageable;
import static br.com.fiap.restaurante.infrastructure.mappers.UsuarioEntityMapper.toDomain;
import static br.com.fiap.restaurante.infrastructure.mappers.UsuarioEntityMapper.toEntity;

@AllArgsConstructor
public class UsuarioRepositoryGateway implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario salvarUsuario(final Usuario usuario) {

        final var usuarioEntity = usuarioRepository.save(toEntity(usuario));

        return toDomain(usuarioEntity);
    }

    @Override
    public void removerUsuario(Long id) {

        var usuarioEntity = UsuarioEntity.builder().id(id).build();

        usuarioRepository.delete(usuarioEntity);
    }

    @Override
    public List<Usuario> listarUsuarios(Integer pagina, Integer tamanhoPagina) {

        var pageable = toPageable(pagina, tamanhoPagina);

        return usuarioRepository.findAll(pageable)
                .stream()
                .map(UsuarioEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Usuario> consultarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).map(UsuarioEntityMapper::toDomain);
    }

    @Override
    public Optional<Usuario> consultarUsuarioPorLogin(String login) {
        return usuarioRepository.findFirstByLogin(login).map(UsuarioEntityMapper::toDomain);
    }
}
