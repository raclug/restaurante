package br.com.fiap.restaurante.application.usercases.tipousuario.impl;

import br.com.fiap.restaurante.application.ports.TipoUsuarioPort;
import br.com.fiap.restaurante.application.usercases.tipousuario.AlterarTipoUsuario;
import br.com.fiap.restaurante.domain.entities.TipoUsuario;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlterarTipoUsuarioImpl implements AlterarTipoUsuario {

    private final TipoUsuarioPort tipoUsuarioPort;

    @Override
    public TipoUsuario execute(final Long id, final TipoUsuario tipoUsuario) {

        var tipoUsuarioConsulta = tipoUsuarioPort.consultarTipoUsuarioPorId(id);

        if (tipoUsuarioConsulta.isEmpty()) {
            throw new RegistroNaoEncontradoException("Tipo Usuário não encontrado.");
        }

        var tipoUsuarioParaAlteracao = new TipoUsuario(
                id,
                tipoUsuario.getNome()
        );

        return tipoUsuarioPort.salvarTipoUsuario(tipoUsuarioParaAlteracao);
    }
}
