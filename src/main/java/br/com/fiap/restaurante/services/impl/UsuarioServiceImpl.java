package br.com.fiap.restaurante.services.impl;

import br.com.fiap.restaurante.dtos.AlteracaoUsuarioDTO;
import br.com.fiap.restaurante.dtos.UsuarioDTO;
import br.com.fiap.restaurante.entities.UsuarioEntity;
import br.com.fiap.restaurante.exceptions.LoginJaExistenteException;
import br.com.fiap.restaurante.exceptions.UsuarioNaoEncontradoException;
import br.com.fiap.restaurante.mappers.UsuarioMapper;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import br.com.fiap.restaurante.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioDTO criarUsuario(final UsuarioDTO usuarioDTO) {

        var loginExistente = usuarioRepository.findFirstByLogin(usuarioDTO.getLogin()).isPresent();

        if (loginExistente) {
            throw new LoginJaExistenteException("Login já está sendo utilizado.");
        }

        var usuarioEntity = usuarioMapper.mapToUsuarioEntity(usuarioDTO);

        usuarioEntity = usuarioRepository.save(usuarioEntity);

        return usuarioMapper.mapToUsuarioDTO(usuarioEntity);
    }

    @Override
    public UsuarioDTO alterarUsuario(final Long id, final AlteracaoUsuarioDTO usuarioDTO) {

        var usuarioEntity = consultarUsuarioEntity(id);

        usuarioMapper.setUsuarioEntityFromUsuarioAlteracao(usuarioEntity, usuarioDTO);

        usuarioEntity = usuarioRepository.save(usuarioEntity);

        return usuarioMapper.mapToUsuarioDTO(usuarioEntity);
    }

    @Override
    public void removerUsuario(final Long id) {

        var usuarioEntity = consultarUsuarioEntity(id);

        usuarioRepository.delete(usuarioEntity);
    }

    @Override
    public List<UsuarioDTO> listarUsuarios(final Pageable pageable) {

        var resultStream = usuarioRepository.findAll(pageable).stream();

        return resultStream.map(usuarioMapper::mapToUsuarioDTO).toList();
    }

    @Override
    public UsuarioDTO consultarUsuario(Long id) {

        var usuarioEntity = consultarUsuarioEntity(id);

        return usuarioMapper.mapToUsuarioDTO(usuarioEntity);
    }

    private UsuarioEntity consultarUsuarioEntity(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new UsuarioNaoEncontradoException("Usuário não encontrado")
        );
    }
}
