package br.com.fiap.restaurante.services.impl;

import br.com.fiap.restaurante.dtos.UsuarioDTO;
import br.com.fiap.restaurante.entities.UsuarioEntity;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import br.com.fiap.restaurante.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public void salvarUsuario(final UsuarioDTO usuarioDTO) {

        var usuarioEntity = UsuarioEntity.builder()
                .nome(usuarioDTO.nome())
                .endereco(usuarioDTO.endereco())
                .tipoUsuario(usuarioDTO.tipoUsuario())
                .email(usuarioDTO.email())
                .login(usuarioDTO.login())
                .build();

        usuarioRepository.save(usuarioEntity);
    }

    @Override
    public void apagarUsuario(final Long id) {

        var usuarioEntity = usuarioRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        usuarioRepository.delete(usuarioEntity);
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {

        var resultStream = StreamSupport.stream(usuarioRepository.findAll().spliterator(), false);

        return resultStream.map(this::mapToUsuarioDTO).toList();
    }

    @Override
    public UsuarioDTO consultarUsuario(Long id) {

        var usuarioDTO = usuarioRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return mapToUsuarioDTO(usuarioDTO);
    }

    private UsuarioDTO mapToUsuarioDTO(final UsuarioEntity usuarioEntity) {
        return new UsuarioDTO(
                usuarioEntity.getId(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getLogin(),
                usuarioEntity.getEndereco(),
                usuarioEntity.getTipoUsuario());
    }
}
