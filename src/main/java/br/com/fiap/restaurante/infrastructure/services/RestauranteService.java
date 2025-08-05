package br.com.fiap.restaurante.infrastructure.services;

import br.com.fiap.restaurante.application.usercases.restaurante.*;
import br.com.fiap.restaurante.domain.entities.Restaurante;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RestauranteService {

    private final CriarRestaurante criarRestaurante;

    private final AlterarRestaurante alterarRestaurante;

    private final ListarRestaurante listarRestaurantes;

    private final ConsultarRestaurante consultarRestaurante;

    private final RemoverRestaurante removerRestaurante;

    @Transactional
    public Restaurante criarRestaurante(final Restaurante restaurante) {
        return criarRestaurante.execute(restaurante);
    }

    @Transactional
    public Restaurante alterarRestaurante(final Long restauranteId, final Restaurante restaurante) {
        return alterarRestaurante.execute(restauranteId, restaurante);
    }

    public Restaurante consultarRestaurante(final Long restauranteId) {
        return consultarRestaurante.execute(restauranteId);
    }

    @Transactional
    public void removerRestaurante(final Long restauranteId) {
        removerRestaurante.execute(restauranteId);
    }

    public List<Restaurante> listarRestaurantes(final Integer pagina, final Integer tamanhoPagina) {
        return listarRestaurantes.execute(pagina, tamanhoPagina);
    }
}
