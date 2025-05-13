package br.com.fiap.restaurante.services;

import br.com.fiap.restaurante.dtos.UsuarioDTO;
import br.com.fiap.restaurante.entities.UsuarioEntity;

import java.util.List;

public interface UsuarioService {

    void salvarUsuario(final UsuarioDTO usuarioDTO);

    void apagarUsuario(final Long id);

    List<UsuarioDTO> listarUsuarios();

    UsuarioDTO consultarUsuario(final Long id);
}
