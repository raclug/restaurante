package br.com.fiap.restaurante.services;

import br.com.fiap.restaurante.dtos.AlteracaoUsuarioDTO;
import br.com.fiap.restaurante.dtos.UsuarioDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO criarUsuario(final UsuarioDTO usuarioDTO);

    UsuarioDTO alterarUsuario(final Long id, final AlteracaoUsuarioDTO usuarioDTO);

    void removerUsuario(final Long id);

    List<UsuarioDTO> listarUsuarios(final Pageable pagina);

    UsuarioDTO consultarUsuario(final Long id);
}
