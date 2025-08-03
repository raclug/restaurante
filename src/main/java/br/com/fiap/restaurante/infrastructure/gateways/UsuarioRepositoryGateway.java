package br.com.fiap.restaurante.infrastructure.gateways;

import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.infrastructure.mappers.UsuarioEntityMapper;
import br.com.fiap.restaurante.infrastructure.persistence.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.fiap.restaurante.infrastructure.mappers.PageableMapper.toPageable;
import static br.com.fiap.restaurante.infrastructure.mappers.UsuarioEntityMapper.toDomain;
import static br.com.fiap.restaurante.infrastructure.mappers.UsuarioEntityMapper.toEntity;

@Component
@AllArgsConstructor
public class UsuarioRepositoryGateway implements UsuarioPort {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario salvarUsuario(final Usuario usuario) {

        final var usuarioEntity = usuarioRepository.save(toEntity(usuario));

        return toDomain(usuarioEntity);
    }

    @Override
    public void removerUsuario(Long id) {
        usuarioRepository.deleteAllById(Collections.singletonList(id));
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

    @Override
    public void adicionarTipoUsuario(Long idUsuario, Long idTipoUsuario) {
        usuarioRepository.addTipoUsuario(idUsuario, idTipoUsuario);
    }
}
