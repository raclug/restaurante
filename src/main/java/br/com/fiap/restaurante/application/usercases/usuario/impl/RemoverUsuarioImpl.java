package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.application.usercases.usuario.RemoverUsuario;
import br.com.fiap.restaurante.exceptions.UsuarioNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoverUsuarioImpl implements RemoverUsuario {

    private final UsuarioPort usuarioPort;

    @Override
    public void removerUsuario(Long id) {
        var usuario = usuarioPort.consultarUsuarioPorId(id);

        if (usuario.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
        }

        usuarioPort.removerUsuario(id);
    }
}
