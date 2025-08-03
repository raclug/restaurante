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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ConsultarItemCardapioImplTest {

    @Mock
    private ItemCardapioPort itemCardapioPort;

    @InjectMocks
    private ConsultarItemCardapioImpl consultarItemCardapio;

    @Test
    void deveRetornarItemQuandoExistente() {
        Long id = 1L;
        ItemCardapio item = ItemCardapio.builder().id(id).nome("Pizza").build();

        when(itemCardapioPort.consultarItemCardapioPorId(id)).thenReturn(Optional.of(item));

        ItemCardapio resultado = consultarItemCardapio.execute(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Pizza", resultado.getNome());
        verify(itemCardapioPort).consultarItemCardapioPorId(id);
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrado() {
        Long id = 2L;
        when(itemCardapioPort.consultarItemCardapioPorId(id)).thenReturn(Optional.empty());

        RegistroNaoEncontradoException ex = assertThrows(
                RegistroNaoEncontradoException.class,
                () -> consultarItemCardapio.execute(id)
        );
        assertEquals("Item cardápio não encontrado.", ex.getMessage());
        verify(itemCardapioPort).consultarItemCardapioPorId(id);
    }
}