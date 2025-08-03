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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlterarItemCardapioImplTest {

    @Mock
    private ItemCardapioPort itemCardapioPort;

    @InjectMocks
    private AlterarItemCardapioImpl alterarItemCardapio;

    @Test
    void deveAlterarItemCardapioQuandoExistente() {
        Long id = 1L;
        ItemCardapio novoItem = ItemCardapio.builder()
                .nome("Novo Nome")
                .descricao("Nova Desc")
                .preco(10.0)
                .disponivelApenasNoRestaurante(true)
                .foto("foto.png")
                .build();

        ItemCardapio existente = ItemCardapio.builder().id(id).build();

        when(itemCardapioPort.consultarItemCardapioPorId(id)).thenReturn(Optional.of(existente));
        when(itemCardapioPort.salvarItemCardapio(any(ItemCardapio.class))).thenAnswer(i -> i.getArgument(0));

        ItemCardapio resultado = alterarItemCardapio.execute(id, novoItem);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Novo Nome", resultado.getNome());
        assertEquals("Nova Desc", resultado.getDescricao());
        assertEquals(10.0, resultado.getPreco());
        assertTrue(resultado.getDisponivelApenasNoRestaurante());
        assertEquals("foto.png", resultado.getFoto());

        verify(itemCardapioPort).consultarItemCardapioPorId(id);
        verify(itemCardapioPort).salvarItemCardapio(any(ItemCardapio.class));
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoExiste() {
        Long id = 2L;
        ItemCardapio item = ItemCardapio.builder().build();

        when(itemCardapioPort.consultarItemCardapioPorId(id)).thenReturn(Optional.empty());

        RegistroNaoEncontradoException ex = assertThrows(
                RegistroNaoEncontradoException.class,
                () -> alterarItemCardapio.execute(id, item)
        );
        assertEquals("Item cardápio não encontrado.", ex.getMessage());
        verify(itemCardapioPort).consultarItemCardapioPorId(id);
        verify(itemCardapioPort, never()).salvarItemCardapio(any());
    }
}