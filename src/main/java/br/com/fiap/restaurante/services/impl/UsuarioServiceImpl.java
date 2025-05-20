package br.com.fiap.restaurante.services.impl;

import br.com.fiap.restaurante.dtos.UsuarioDTO;
import br.com.fiap.restaurante.entities.SenhaEntity;
import br.com.fiap.restaurante.entities.UsuarioEntity;
import br.com.fiap.restaurante.mappers.UsuarioMapper;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import br.com.fiap.restaurante.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.fiap.restaurante.mappers.EnderecoMapper.mapToEnderecoEntity;
import static br.com.fiap.restaurante.mappers.UsuarioMapper.mapToUsuarioDTO;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTO criarUsuario(final UsuarioDTO usuarioDTO) {

        var enderecoEntity = mapToEnderecoEntity(usuarioDTO.getEndereco());

        var senhaEntity = SenhaEntity.builder().senha(passwordEncoder.encode(usuarioDTO.getSenha())).build();

        var usuarioEntity = UsuarioEntity.builder()
                .nome(usuarioDTO.getNome())
                .endereco(enderecoEntity)
                .tipoUsuario(usuarioDTO.getTipoUsuario())
                .email(usuarioDTO.getEmail())
                .senha(senhaEntity)
                .login(usuarioDTO.getLogin())
                .build();

        usuarioEntity = usuarioRepository.save(usuarioEntity);

        return mapToUsuarioDTO(usuarioEntity);
    }

    @Override
    public UsuarioDTO alterarUsuario(final UsuarioDTO usuarioDTO) {

        var usuarioEntity = usuarioRepository.findById(usuarioDTO.getId()).orElseThrow(IllegalArgumentException::new);

        var enderecoEntity = mapToEnderecoEntity(usuarioDTO.getEndereco());

        usuarioEntity.setNome(usuarioDTO.getNome());
        usuarioEntity.setEndereco(enderecoEntity);
        usuarioEntity.setTipoUsuario(usuarioDTO.getTipoUsuario());
        usuarioEntity.setEmail(usuarioDTO.getEmail());
        usuarioEntity.setLogin(usuarioDTO.getLogin());

        usuarioEntity = usuarioRepository.save(usuarioEntity);

        return mapToUsuarioDTO(usuarioEntity);
    }

    @Override
    public void removerUsuario(final Long id) {

        var usuarioEntity = usuarioRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        usuarioRepository.delete(usuarioEntity);
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {

        var resultStream = usuarioRepository.findAll().stream();

        return resultStream.map(UsuarioMapper::mapToUsuarioDTO).toList();
    }

    @Override
    public UsuarioDTO consultarUsuario(Long id) {

        var usuarioEntity = usuarioRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return mapToUsuarioDTO(usuarioEntity);
    }
}
