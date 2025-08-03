package br.com.fiap.restaurante.application.usercases.ItemCardapio.impl;

import br.com.fiap.restaurante.application.ports.ItemCardapioPort;
import br.com.fiap.restaurante.domain.entities.ItemCardapio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarItemCardapioImplTest {

    @Mock
    private ItemCardapioPort itemCardapioPort;

    @InjectMocks
    private CriarItemCardapioImpl criarItemCardapio;

    @Test
    void deveCriarItemCardapioComSucesso() {
        ItemCardapio item = ItemCardapio.builder().build();

        when(itemCardapioPort.salvarItemCardapio(item)).thenReturn(item);

        criarItemCardapio.execute(item);

        verify(itemCardapioPort).salvarItemCardapio(item);
    }
}