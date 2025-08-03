package br.com.fiap.restaurante.application.usercases.tipousuario;

import br.com.fiap.restaurante.domain.entities.TipoUsuario;

import java.util.List;

public interface ListarTiposUsuario {

    List<TipoUsuario> execute(Integer pagina, Integer tamanhoPagina);
}
