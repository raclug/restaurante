package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.gateways.UsuarioGateway;
import br.com.fiap.restaurante.application.usercases.usuario.ConsultarUsuario;
import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.exceptions.UsuarioNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConsultarUsuarioImpl implements ConsultarUsuario {

    private final UsuarioGateway usuarioGateway;

    @Override
    public Usuario consultarUsuario(final Long id) {
        var usuarioConsulta = usuarioGateway.consultarUsuarioPorId(id);

        if (usuarioConsulta.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
        }

        return usuarioConsulta.get();
    }
}
