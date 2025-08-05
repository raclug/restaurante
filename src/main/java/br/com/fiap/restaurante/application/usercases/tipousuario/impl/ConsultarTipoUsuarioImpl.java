package br.com.fiap.restaurante.application.usercases.tipousuario.impl;

import br.com.fiap.restaurante.application.ports.TipoUsuarioPort;
import br.com.fiap.restaurante.application.usercases.tipousuario.ConsultarTipoUsuario;
import br.com.fiap.restaurante.domain.entities.TipoUsuario;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConsultarTipoUsuarioImpl implements ConsultarTipoUsuario {

    private final TipoUsuarioPort tipoUsuarioPort;

    @Override
    public TipoUsuario execute(final Long id) {
        var tipoUsuarioConsulta = tipoUsuarioPort.consultarTipoUsuarioPorId(id);

        if (tipoUsuarioConsulta.isEmpty()) {
            throw new RegistroNaoEncontradoException("Tipo Usuário não encontrado.");
        }

        return tipoUsuarioConsulta.get();
    }
}
