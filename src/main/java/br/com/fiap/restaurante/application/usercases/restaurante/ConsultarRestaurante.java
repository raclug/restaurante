package br.com.fiap.restaurante.application.usercases.restaurante;

import br.com.fiap.restaurante.domain.entities.Restaurante;

public interface ConsultarRestaurante {

    Restaurante execute(Long id);
}
