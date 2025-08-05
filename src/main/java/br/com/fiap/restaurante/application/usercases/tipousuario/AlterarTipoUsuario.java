package br.com.fiap.restaurante.application.usercases.tipousuario;

import br.com.fiap.restaurante.domain.entities.TipoUsuario;

public interface AlterarTipoUsuario {

    TipoUsuario execute(Long id, TipoUsuario tipoUsuario);
}
