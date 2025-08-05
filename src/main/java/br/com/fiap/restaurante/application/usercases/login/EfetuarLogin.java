package br.com.fiap.restaurante.application.usercases.login;

import br.com.fiap.restaurante.domain.entities.Login;

public interface EfetuarLogin {

    void execute(Login login);
}
