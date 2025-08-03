package br.com.fiap.restaurante.infrastructure.services;

import br.com.fiap.restaurante.application.usercases.ItemCardapio.*;
import br.com.fiap.restaurante.domain.entities.ItemCardapio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemCardapioServiceTest {
    @Mock
    private CriarItemCardapio criarItemCardapio;
    @Mock
    private AlterarItemCardapio alterarItemCardapio;
    @Mock
    private ListarItensCardapio listarItensCardapio;
    @Mock
    private ConsultarItemCardapio consultarItemCardapio;
    @Mock
    private RemoverItemCardapio removerItemCardapio;

    @InjectMocks
    private ItemCardapioService service;

    @Test
    void deveCriarItemCardapio() {
        ItemCardapio item = ItemCardapio.builder().id(1L).nome("Pizza").build();
        when(criarItemCardapio.execute(item)).thenReturn(item);

        ItemCardapio result = service.criarItemCardapio(item);

        assertNotNull(result);
        assertEquals("Pizza", result.getNome());
        verify(criarItemCardapio).execute(item);
    }

    @Test
    void deveAlterarItemCardapio() {
        ItemCardapio item = ItemCardapio.builder().id(2L).nome("Lasanha").build();
        when(alterarItemCardapio.execute(2L, item)).thenReturn(item);

        ItemCardapio result = service.alterarItemCardapio(2L, item);

        assertNotNull(result);
        assertEquals("Lasanha", result.getNome());
        verify(alterarItemCardapio).execute(2L, item);
    }

    @Test
    void deveConsultarItemCardapio() {
        ItemCardapio item = ItemCardapio.builder().id(3L).nome("Risoto").build();
        when(consultarItemCardapio.execute(3L)).thenReturn(item);

        ItemCardapio result = service.consultarItemCardapio(3L);

        assertNotNull(result);
        assertEquals("Risoto", result.getNome());
        verify(consultarItemCardapio).execute(3L);
    }

    @Test
    void deveRemoverItemCardapio() {
        service.removerItemCardapio(4L);
        verify(removerItemCardapio).execute(4L);
    }

    @Test
    void deveListarItensCardapio() {
        ItemCardapio item = ItemCardapio.builder().id(5L).nome("Salada").build();
        when(listarItensCardapio.execute(0, 10)).thenReturn(List.of(item));

        List<ItemCardapio> itens = service.listarItensCardapio(0, 10);

        assertNotNull(itens);
        assertEquals(1, itens.size());
        assertEquals("Salada", itens.get(0).getNome());
        verify(listarItensCardapio).execute(0, 10);
    }
}