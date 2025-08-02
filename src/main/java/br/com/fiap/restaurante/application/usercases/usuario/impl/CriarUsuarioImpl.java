package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.gateways.UsuarioGateway;
import br.com.fiap.restaurante.application.usercases.usuario.CriarUsuario;
import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.exceptions.LoginJaExistenteException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriarUsuarioImpl implements CriarUsuario {

    private final UsuarioGateway usuarioGateway;

    @Override
    public Usuario criarUsuario(final Usuario usuario) {

        var loginExistente = usuarioGateway.consultarUsuarioPorLogin(usuario.login());

        if (loginExistente.isPresent()) {
            throw new LoginJaExistenteException("Login já está sendo utilizado.");
        }

        return usuarioGateway.salvarUsuario(usuario);
    }
}
