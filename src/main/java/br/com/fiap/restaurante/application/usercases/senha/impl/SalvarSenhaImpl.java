package br.com.fiap.restaurante.application.usercases.senha.impl;

import br.com.fiap.restaurante.application.ports.PasswordEncoderPort;
import br.com.fiap.restaurante.application.ports.SenhaPort;
import br.com.fiap.restaurante.application.usercases.senha.SalvarSenha;
import br.com.fiap.restaurante.domain.entities.Senha;
import br.com.fiap.restaurante.exceptions.NaoAutorizadoException;
import br.com.fiap.restaurante.exceptions.UsuarioNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SalvarSenhaImpl implements SalvarSenha {

    private final PasswordEncoderPort passwordEncoderPort;

    private final SenhaPort senhaPort;

    @Override
    public void salvarSenha(final Long usuarioId, final String senhaAtual, final String novaSenha) {

        var senhaCadastrada = senhaPort.consultarSenhaPorUsuarioId(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado."));

        if (!passwordEncoderPort.matches(senhaAtual, senhaCadastrada.senha())) {
            throw new NaoAutorizadoException("Senha atual inválida");
        }

        var senhaAlterada = new Senha(usuarioId, passwordEncoderPort.encode(novaSenha));

        senhaPort.salvarSenha(senhaAlterada);
    }
}
