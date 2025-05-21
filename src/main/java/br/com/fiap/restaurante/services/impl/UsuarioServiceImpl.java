package br.com.fiap.restaurante.services.impl;

import br.com.fiap.restaurante.dtos.LoginDTO;
import br.com.fiap.restaurante.dtos.SenhaDTO;
import br.com.fiap.restaurante.dtos.UsuarioDTO;
import br.com.fiap.restaurante.entities.SenhaEntity;
import br.com.fiap.restaurante.entities.UsuarioEntity;
import br.com.fiap.restaurante.exceptions.UsuarioNaoAutorizadoException;
import br.com.fiap.restaurante.exceptions.UsuarioNaoEncontradoException;
import br.com.fiap.restaurante.mappers.UsuarioMapper;
import br.com.fiap.restaurante.repositories.SenhaRepository;
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

    private final SenhaRepository senhaRepository;

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
    public UsuarioDTO alterarUsuario(final Long id, final UsuarioDTO usuarioDTO) {

        var usuarioEntity = consultarUsuarioEntity(id);

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
    public void alterarSenha(final Long id, final SenhaDTO senhaDTO) {

        var usuarioEntity = consultarUsuarioEntity(id);

        var senhaEntity = usuarioEntity.getSenha();

        senhaEntity.setSenha(passwordEncoder.encode(senhaDTO.getSenha()));

        senhaRepository.save(senhaEntity);
    }


    @Override
    public void removerUsuario(final Long id) {

        var usuarioEntity = consultarUsuarioEntity(id);

        usuarioRepository.delete(usuarioEntity);
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {

        var resultStream = usuarioRepository.findAll().stream();

        return resultStream.map(UsuarioMapper::mapToUsuarioDTO).toList();
    }

    @Override
    public UsuarioDTO consultarUsuario(Long id) {

        var usuarioEntity = consultarUsuarioEntity(id);

        return mapToUsuarioDTO(usuarioEntity);
    }

    @Override
    public void validarLogin(final LoginDTO loginDTO) {

        var usuarioEntity = usuarioRepository.findFirstByLogin(loginDTO.getLogin()).orElseThrow(() ->
                new UsuarioNaoAutorizadoException("Login ou senha inválidos")
        );

        var senhaCriptografada = usuarioEntity.getSenha().getSenha();

        if (!passwordEncoder.matches(loginDTO.getSenha(), senhaCriptografada)) {
            throw new UsuarioNaoAutorizadoException("Login ou senha inválidos");
        }
    }

    private UsuarioEntity consultarUsuarioEntity(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new UsuarioNaoEncontradoException("Usuário não encontrado")
        );
    }
}
