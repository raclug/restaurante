package br.com.fiap.restaurante.services;

import br.com.fiap.restaurante.dtos.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO criarUsuario(final UsuarioDTO usuarioDTO);

    UsuarioDTO alterarUsuario(final UsuarioDTO usuarioDTO);

    void removerUsuario(final Long id);

    List<UsuarioDTO> listarUsuarios();

    UsuarioDTO consultarUsuario(final Long id);
}
