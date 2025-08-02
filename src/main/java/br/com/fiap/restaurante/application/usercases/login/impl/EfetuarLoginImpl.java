package br.com.fiap.restaurante.application.usercases.login.impl;

import br.com.fiap.restaurante.application.ports.PasswordEncoderPort;
import br.com.fiap.restaurante.application.ports.SenhaPort;
import br.com.fiap.restaurante.application.usercases.login.EfetuarLogin;
import br.com.fiap.restaurante.domain.entities.Login;
import br.com.fiap.restaurante.domain.exceptions.NaoAutorizadoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EfetuarLoginImpl implements EfetuarLogin {

    private final SenhaPort senhaPort;

    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public void execute(final Login login) {
        var senhaCadastrada = senhaPort.consultarSenhaPorLogin(login.login()).orElseThrow(() ->
                new NaoAutorizadoException("Login ou senha inválidos")
        );

        var senhaCriptografada = senhaCadastrada.senha();

        if (!passwordEncoderPort.matches(login.senha(), senhaCriptografada)) {
            throw new NaoAutorizadoException("Login ou senha inválidos");
        }
    }
}
