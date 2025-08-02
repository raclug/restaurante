package br.com.fiap.restaurante.application.usercases.senha.impl;

import br.com.fiap.restaurante.application.ports.PasswordEncoderPort;
import br.com.fiap.restaurante.application.ports.SenhaPort;
import br.com.fiap.restaurante.application.usercases.senha.SalvarSenha;
import br.com.fiap.restaurante.domain.entities.Senha;
import br.com.fiap.restaurante.domain.exceptions.NaoAutorizadoException;
import br.com.fiap.restaurante.domain.exceptions.UsuarioNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SalvarSenhaImpl implements SalvarSenha {

    private final PasswordEncoderPort passwordEncoderPort;

    private final SenhaPort senhaPort;

    @Override
    public void execute(final Long usuarioId, final String senhaAtual, final String novaSenha) {

        var senhaCadastrada = senhaPort.consultarSenhaPorUsuarioId(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado."));

        if (passwordEncoderPort.matches(senhaAtual, senhaCadastrada.getSenha())) {
            throw new NaoAutorizadoException("Senha atual inválida");
        }

        var senhaAlterada = new Senha(senhaCadastrada.getId(), usuarioId, passwordEncoderPort.encode(novaSenha));

        senhaPort.salvarSenha(senhaAlterada);
    }
}
