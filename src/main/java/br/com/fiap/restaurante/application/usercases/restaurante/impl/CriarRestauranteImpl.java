package br.com.fiap.restaurante.application.usercases.restaurante.impl;

import br.com.fiap.restaurante.application.ports.RestaurantePort;
import br.com.fiap.restaurante.application.ports.TipoUsuarioPort;
import br.com.fiap.restaurante.application.usercases.restaurante.CriarRestaurante;
import br.com.fiap.restaurante.domain.entities.Restaurante;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import br.com.fiap.restaurante.domain.exceptions.TipoUsuarioException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriarRestauranteImpl implements CriarRestaurante {

    private final RestaurantePort restaurantePort;

    private final TipoUsuarioPort tipoUsuarioPort;

    @Override
    public Restaurante execute(final Restaurante restaurante) {

        var tipoUsuario = tipoUsuarioPort.consultarTipoUsuarioPorId(
                        restaurante.getResponsavel().getTipoUsuario().getId())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Tipo de usuário não encontrado."));

        if (!"Dono de Restaurante".equals(tipoUsuario.getNome())) {
            throw new TipoUsuarioException(
                    "Somente usuário do tipo 'Dono de Restaurante' pode ser responsável do restaurante.");
        }

        return restaurantePort.salvarRestaurante(restaurante);
    }
}
