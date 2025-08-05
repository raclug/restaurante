package br.com.fiap.restaurante.application.usercases.tipousuario.impl;

import br.com.fiap.restaurante.application.ports.TipoUsuarioPort;
import br.com.fiap.restaurante.application.usercases.tipousuario.CriarTipoUsuario;
import br.com.fiap.restaurante.domain.entities.TipoUsuario;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriarTipoUsuarioImpl implements CriarTipoUsuario {

    private final TipoUsuarioPort tipoUsuarioPort;

    @Override
    public TipoUsuario execute(final TipoUsuario tipoUsuario) {
        return tipoUsuarioPort.salvarTipoUsuario(tipoUsuario);
    }
}
