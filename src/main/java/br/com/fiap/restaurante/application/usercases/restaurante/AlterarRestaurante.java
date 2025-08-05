package br.com.fiap.restaurante.application.usercases.restaurante;

import br.com.fiap.restaurante.domain.entities.Restaurante;

public interface AlterarRestaurante {

    Restaurante execute(Long id, Restaurante restaurante);
}
