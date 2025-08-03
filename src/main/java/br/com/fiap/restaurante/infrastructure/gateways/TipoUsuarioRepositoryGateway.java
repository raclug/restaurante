package br.com.fiap.restaurante.infrastructure.gateways;

import br.com.fiap.restaurante.application.ports.TipoUsuarioPort;
import br.com.fiap.restaurante.domain.entities.TipoUsuario;
import br.com.fiap.restaurante.infrastructure.mappers.TipoUsuarioEntityMapper;
import br.com.fiap.restaurante.infrastructure.persistence.repositories.TipoUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.fiap.restaurante.infrastructure.mappers.PageableMapper.toPageable;
import static br.com.fiap.restaurante.infrastructure.mappers.TipoUsuarioEntityMapper.toDomain;
import static br.com.fiap.restaurante.infrastructure.mappers.TipoUsuarioEntityMapper.toEntity;

@Component
@AllArgsConstructor
public class TipoUsuarioRepositoryGateway implements TipoUsuarioPort {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public TipoUsuario salvarTipoUsuario(final TipoUsuario tipoUsuario) {

        final var tipoUsuarioEntity = tipoUsuarioRepository.save(toEntity(tipoUsuario));

        return toDomain(tipoUsuarioEntity);
    }

    @Override
    public void removerTipoUsuario(Long id) {
        tipoUsuarioRepository.deleteAllById(Collections.singletonList(id));
    }

    @Override
    public List<TipoUsuario> listarTiposUsuario(Integer pagina, Integer tamanhoPagina) {

        var pageable = toPageable(pagina, tamanhoPagina);

        return tipoUsuarioRepository.findAll(pageable)
                .stream()
                .map(TipoUsuarioEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<TipoUsuario> consultarTipoUsuarioPorId(Long id) {
        return tipoUsuarioRepository.findById(id).map(TipoUsuarioEntityMapper::toDomain);
    }
}
