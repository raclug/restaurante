package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.ports.PasswordEncoderPort;
import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.application.usercases.usuario.CriarUsuario;
import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.domain.exceptions.LoginJaExistenteException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriarUsuarioImpl implements CriarUsuario {

    private final UsuarioPort usuarioPort;

    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public Usuario execute(final Usuario usuario) {

        var loginExistente = usuarioPort.consultarUsuarioPorLogin(usuario.getLogin());

        if (loginExistente.isPresent()) {
            throw new LoginJaExistenteException("Login já está sendo utilizado.");
        }

        usuario.getSenha().setSenha(passwordEncoderPort.encode(usuario.getSenha().getSenha()));

        return usuarioPort.salvarUsuario(usuario);
    }
}
