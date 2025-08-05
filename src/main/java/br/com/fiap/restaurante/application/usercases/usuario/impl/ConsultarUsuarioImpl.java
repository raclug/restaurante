package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.application.usercases.usuario.ConsultarUsuario;
import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConsultarUsuarioImpl implements ConsultarUsuario {

    private final UsuarioPort usuarioPort;

    @Override
    public Usuario execute(final Long id) {
        var usuarioConsulta = usuarioPort.consultarUsuarioPorId(id);

        if (usuarioConsulta.isEmpty()) {
            throw new RegistroNaoEncontradoException("Usuário não encontrado.");
        }

        return usuarioConsulta.get();
    }
}
