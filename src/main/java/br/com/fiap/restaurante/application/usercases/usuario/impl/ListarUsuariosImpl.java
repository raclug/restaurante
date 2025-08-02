package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.application.usercases.usuario.ListarUsuarios;
import br.com.fiap.restaurante.domain.entities.Usuario;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ListarUsuariosImpl implements ListarUsuarios {

    private final UsuarioPort usuarioPort;

    @Override
    public List<Usuario> execute(final Integer pagina, final Integer tamanhoPagina) {

        return usuarioPort.listarUsuarios(pagina, tamanhoPagina);
    }
}
