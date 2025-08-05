package br.com.fiap.restaurante.application.usercases.ItemCardapio.impl;

import br.com.fiap.restaurante.application.ports.ItemCardapioPort;
import br.com.fiap.restaurante.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RemoverItemCardapioImplTest {

    @Mock
    private ItemCardapioPort itemCardapioPort;

    @InjectMocks
    private RemoverItemCardapioImpl removerItemCardapio;

    @Test
    void deveRemoverItemCardapioQuandoExistente() {
        Long id = 1L;
        when(itemCardapioPort.consultarItemCardapioPorId(id))
                .thenReturn(Optional.of(ItemCardapio.builder().id(id).build()));

        removerItemCardapio.execute(id);

        verify(itemCardapioPort).consultarItemCardapioPorId(id);
        verify(itemCardapioPort).removerItemCardapio(id);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoExiste() {
        Long id = 2L;
        when(itemCardapioPort.consultarItemCardapioPorId(id)).thenReturn(Optional.empty());

        RegistroNaoEncontradoException ex = assertThrows(
                RegistroNaoEncontradoException.class,
                () -> removerItemCardapio.execute(id)
        );
        assertEquals("Item cardápio não encontrado.", ex.getMessage());
        verify(itemCardapioPort).consultarItemCardapioPorId(id);
        verify(itemCardapioPort, never()).removerItemCardapio(any());
    }

}