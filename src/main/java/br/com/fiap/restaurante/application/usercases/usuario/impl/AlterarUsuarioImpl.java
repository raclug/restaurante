package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.application.usercases.usuario.AlterarUsuario;
import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.exceptions.UsuarioNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlterarUsuarioImpl implements AlterarUsuario {

    private final UsuarioPort usuarioPort;

    @Override
    public Usuario alterarUsuario(final Usuario usuario) {

        var usuarioConsulta = usuarioPort.consultarUsuarioPorId(usuario.id());

        if (usuarioConsulta.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
        }

        var usuarioParaAlteracao = new Usuario(
                usuario.id(),
                usuario.nome(),
                usuario.email(),
                usuarioConsulta.get().login(),
                null,
                usuario.tipoUsuario(),
                usuario.endereco());

        return usuarioPort.salvarUsuario(usuarioParaAlteracao);
    }
}
