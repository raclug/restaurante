package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.gateways.UsuarioGateway;
import br.com.fiap.restaurante.application.usercases.usuario.RemoverUsuario;
import br.com.fiap.restaurante.exceptions.UsuarioNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoverUsuarioImpl implements RemoverUsuario {

    private final UsuarioGateway usuarioGateway;

    @Override
    public void removerUsuario(Long id) {
        var usuario = usuarioGateway.consultarUsuarioPorId(id);

        if (usuario.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
        }

        usuarioGateway.removerUsuario(id);
    }
}
