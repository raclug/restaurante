package br.com.fiap.restaurante.application.usercases.ItemCardapio.impl;

import br.com.fiap.restaurante.application.ports.ItemCardapioPort;
import br.com.fiap.restaurante.domain.entities.ItemCardapio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ListarItensCardapioImplTest {

    @Mock
    private ItemCardapioPort itemCardapioPort;

    @InjectMocks
    private ListarItensCardapioImpl listarItensCardapio;

    @Test
    void deveListarItensCardapioComSucesso() {
        Integer pagina = 0;
        Integer tamanho = 2;
        List<ItemCardapio> itens = Arrays.asList(
                ItemCardapio.builder().id(1L).nome("Pizza").build(),
                ItemCardapio.builder().id(2L).nome("Lasanha").build()
        );

        when(itemCardapioPort.listarItensCardapio(pagina, tamanho)).thenReturn(itens);

        List<ItemCardapio> resultado = listarItensCardapio.execute(pagina, tamanho);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(itemCardapioPort).listarItensCardapio(pagina, tamanho);
    }

}