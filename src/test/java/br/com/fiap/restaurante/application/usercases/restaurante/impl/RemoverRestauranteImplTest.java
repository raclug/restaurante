package br.com.fiap.restaurante.application.usercases.restaurante.impl;

import br.com.fiap.restaurante.application.ports.RestaurantePort;
import br.com.fiap.restaurante.domain.entities.Restaurante;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoverRestauranteImplTest {
    @Mock
    private RestaurantePort restaurantePort;

    @InjectMocks
    private RemoverRestauranteImpl removerRestaurante;

    @Test
    void deveRemoverRestauranteComSucesso() {
        Long id = 1L;
        Restaurante restaurante = mock(Restaurante.class);

        when(restaurantePort.consultarRestaurantePorId(id)).thenReturn(Optional.of(restaurante));

        assertDoesNotThrow(() -> removerRestaurante.execute(id));
        verify(restaurantePort).consultarRestaurantePorId(id);
        verify(restaurantePort).removerRestaurante(id);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        Long id = 2L;
        when(restaurantePort.consultarRestaurantePorId(id)).thenReturn(Optional.empty());

        RegistroNaoEncontradoException ex = assertThrows(
                RegistroNaoEncontradoException.class,
                () -> removerRestaurante.execute(id)
        );
        assertEquals("Restaurante não encontrado.", ex.getMessage());
        verify(restaurantePort).consultarRestaurantePorId(id);
        verify(restaurantePort, never()).removerRestaurante(any());
    }
}