package br.com.fiap.restaurante.application.usercases.tipousuario;

import br.com.fiap.restaurante.domain.entities.TipoUsuario;

public interface ConsultarTipoUsuario {

    TipoUsuario execute(Long id);
}
