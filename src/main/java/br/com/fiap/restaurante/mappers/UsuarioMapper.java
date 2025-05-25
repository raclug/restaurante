package br.com.fiap.restaurante.mappers;

import br.com.fiap.restaurante.dtos.AlteracaoUsuarioDTO;
import br.com.fiap.restaurante.dtos.UsuarioDTO;
import br.com.fiap.restaurante.entities.SenhaEntity;
import br.com.fiap.restaurante.entities.UsuarioEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UsuarioMapper {

    private final EnderecoMapper enderecoMapper;

    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO mapToUsuarioDTO(final UsuarioEntity usuarioEntity) {

        var enderecoEntity = usuarioEntity.getEndereco();

        var enderecoDTO = enderecoMapper.mapToEnderecoDTO(enderecoEntity);

        return UsuarioDTO.builder().id(usuarioEntity.getId())
                .nome(usuarioEntity.getNome())
                .email(usuarioEntity.getEmail())
                .login(usuarioEntity.getLogin())
                .endereco(enderecoDTO)
                .tipoUsuario(usuarioEntity.getTipoUsuario()).build();
    }

    public UsuarioEntity mapToUsuarioEntity(final UsuarioDTO usuarioDTO) {

        var enderecoEntity = enderecoMapper.mapToEnderecoEntity(usuarioDTO.getEndereco());

        var senhaEntity = SenhaEntity.builder().senha(passwordEncoder.encode(usuarioDTO.getSenha())).build();

        return UsuarioEntity.builder()
                .nome(usuarioDTO.getNome())
                .endereco(enderecoEntity)
                .tipoUsuario(usuarioDTO.getTipoUsuario())
                .email(usuarioDTO.getEmail())
                .senha(senhaEntity)
                .login(usuarioDTO.getLogin())
                .build();
    }

    public void setUsuarioEntityFromUsuarioAlteracao(
            final UsuarioEntity usuarioEntity, final AlteracaoUsuarioDTO usuarioDTO) {

        var enderecoEntity = enderecoMapper.mapToEnderecoEntity(usuarioDTO.getEndereco());

        usuarioEntity.setNome(usuarioDTO.getNome());
        usuarioEntity.setEndereco(enderecoEntity);
        usuarioEntity.setTipoUsuario(usuarioDTO.getTipoUsuario());
        usuarioEntity.setEmail(usuarioDTO.getEmail());
    }
}
