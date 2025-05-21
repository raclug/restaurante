package br.com.fiap.restaurante.services;

import br.com.fiap.restaurante.dtos.LoginDTO;
import br.com.fiap.restaurante.dtos.SenhaDTO;
import br.com.fiap.restaurante.dtos.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO criarUsuario(final UsuarioDTO usuarioDTO);

    UsuarioDTO alterarUsuario(final Long id, final UsuarioDTO usuarioDTO);

    void alterarSenha(final Long id, final SenhaDTO senhaDTO);

    void removerUsuario(final Long id);

    List<UsuarioDTO> listarUsuarios();

    UsuarioDTO consultarUsuario(final Long id);

    void validarLogin(final LoginDTO loginDTO);
}
