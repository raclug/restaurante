package br.com.fiap.restaurante.application.usercases.tipousuario.impl;

import br.com.fiap.restaurante.application.ports.TipoUsuarioPort;
import br.com.fiap.restaurante.application.usercases.tipousuario.RemoverTipoUsuario;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoverTipoUsuarioImpl implements RemoverTipoUsuario {

    private final TipoUsuarioPort tipoUsuarioPort;

    @Override
    public void execute(Long id) {
        var tipoUsuario = tipoUsuarioPort.consultarTipoUsuarioPorId(id);

        if (tipoUsuario.isEmpty()) {
            throw new RegistroNaoEncontradoException("Tipo Usuário não encontrado.");
        }

        tipoUsuarioPort.removerTipoUsuario(id);
    }
}
