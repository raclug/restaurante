package br.com.fiap.restaurante.application.usercases.tipousuario.impl;

import br.com.fiap.restaurante.application.ports.TipoUsuarioPort;
import br.com.fiap.restaurante.application.usercases.tipousuario.ListarTiposUsuario;
import br.com.fiap.restaurante.domain.entities.TipoUsuario;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ListarTiposUsuarioImpl implements ListarTiposUsuario {

    private final TipoUsuarioPort tipoUsuarioPort;

    @Override
    public List<TipoUsuario> execute(final Integer pagina, final Integer tamanhoPagina) {

        return tipoUsuarioPort.listarTiposUsuario(pagina, tamanhoPagina);
    }
}
