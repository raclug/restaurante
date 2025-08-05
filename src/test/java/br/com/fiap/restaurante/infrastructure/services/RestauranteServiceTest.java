package br.com.fiap.restaurante.infrastructure.services;

import br.com.fiap.restaurante.application.usercases.restaurante.*;
import br.com.fiap.restaurante.domain.entities.Restaurante;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RestauranteServiceTest {
    @Mock
    private CriarRestaurante criarRestaurante;
    @Mock
    private AlterarRestaurante alterarRestaurante;
    @Mock
    private ListarRestaurante listarRestaurantes;
    @Mock
    private ConsultarRestaurante consultarRestaurante;
    @Mock
    private RemoverRestaurante removerRestaurante;

    @InjectMocks
    private RestauranteService service;

    @Test
    void deveCriarRestaurante() {
        Restaurante restaurante = Restaurante.builder().id(1L).nome("Restaurante A").build();
        when(criarRestaurante.execute(restaurante)).thenReturn(restaurante);

        Restaurante result = service.criarRestaurante(restaurante);

        assertNotNull(result);
        assertEquals("Restaurante A", result.getNome());
        verify(criarRestaurante).execute(restaurante);
    }

    @Test
    void deveAlterarRestaurante() {
        Restaurante restaurante = Restaurante.builder().id(2L).nome("Restaurante B").build();
        when(alterarRestaurante.execute(2L, restaurante)).thenReturn(restaurante);

        Restaurante result = service.alterarRestaurante(2L, restaurante);

        assertNotNull(result);
        assertEquals("Restaurante B", result.getNome());
        verify(alterarRestaurante).execute(2L, restaurante);
    }

    @Test
    void deveConsultarRestaurante() {
        Restaurante restaurante = Restaurante.builder().id(3L).nome("Restaurante C").build();
        when(consultarRestaurante.execute(3L)).thenReturn(restaurante);

        Restaurante result = service.consultarRestaurante(3L);

        assertNotNull(result);
        assertEquals("Restaurante C", result.getNome());
        verify(consultarRestaurante).execute(3L);
    }

    @Test
    void deveRemoverRestaurante() {
        service.removerRestaurante(4L);
        verify(removerRestaurante).execute(4L);
    }

    @Test
    void deveListarRestaurantes() {
        Restaurante restaurante = Restaurante.builder().id(5L).nome("Restaurante D").build();
        when(listarRestaurantes.execute(0, 10)).thenReturn(List.of(restaurante));

        List<Restaurante> restaurantes = service.listarRestaurantes(0, 10);

        assertNotNull(restaurantes);
        assertEquals(1, restaurantes.size());
        assertEquals("Restaurante D", restaurantes.get(0).getNome());
        verify(listarRestaurantes).execute(0, 10);
    }
}