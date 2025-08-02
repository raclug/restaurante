package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.gateways.UsuarioGateway;
import br.com.fiap.restaurante.application.usercases.usuario.ListarUsuarios;
import br.com.fiap.restaurante.domain.entities.Usuario;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ListarUsuariosImpl implements ListarUsuarios {

    private final UsuarioGateway usuarioGateway;

    @Override
    public List<Usuario> listarUsuarios(final Integer pagina,
                                        final Integer tamanhoPagina) {

        return usuarioGateway.listarUsuarios(pagina, tamanhoPagina);
    }
}
