package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.ports.TipoUsuarioPort;
import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.application.usercases.usuario.AdicionarTipoUsuario;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdicionarTipoUsuarioImpl implements AdicionarTipoUsuario {

    private final UsuarioPort usuarioPort;

    private final TipoUsuarioPort tipoUsuarioPort;

    @Override
    public void execute(final Long idUsuario, final Long idTipoUsuario) {

        var usuarioConsulta = usuarioPort.consultarUsuarioPorId(idUsuario);
        var tipoUsuarioConsulta = tipoUsuarioPort.consultarTipoUsuarioPorId(idTipoUsuario);

        if (usuarioConsulta.isEmpty()) {
            throw new RegistroNaoEncontradoException("Usuário não encontrado.");
        }

        if (tipoUsuarioConsulta.isEmpty()) {
            throw new RegistroNaoEncontradoException("Tipo de Usuário não encontrado.");
        }

        usuarioPort.adicionarTipoUsuario(idUsuario, idTipoUsuario);
    }
}
