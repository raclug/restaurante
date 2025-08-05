package br.com.fiap.restaurante.application.ports;

import br.com.fiap.restaurante.domain.entities.Senha;

import java.util.Optional;

public interface SenhaPort {

    Optional<Senha> consultarSenhaPorUsuarioId(Long usuarioId);

    Optional<Senha> consultarSenhaPorLogin(String login);

    void salvarSenha(Senha senha);
}
