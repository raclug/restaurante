package br.com.fiap.restaurante.application.usercases.restaurante.impl;

import br.com.fiap.restaurante.application.ports.RestaurantePort;
import br.com.fiap.restaurante.domain.entities.Restaurante;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarRestauranteImplTest {
    @Mock
    private RestaurantePort restaurantePort;

    @InjectMocks
    private ListarRestauranteImpl listarRestaurante;

    @Test
    void deveListarRestaurantesComSucesso() {
        int pagina = 0;
        int tamanhoPagina = 10;
        List<Restaurante> restaurantes = List.of(
                mock(Restaurante.class),
                mock(Restaurante.class)
        );

        when(restaurantePort.listarRestaurante(pagina, tamanhoPagina)).thenReturn(restaurantes);

        List<Restaurante> resultado = listarRestaurante.execute(pagina, tamanhoPagina);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(restaurantePort).listarRestaurante(pagina, tamanhoPagina);
    }
}