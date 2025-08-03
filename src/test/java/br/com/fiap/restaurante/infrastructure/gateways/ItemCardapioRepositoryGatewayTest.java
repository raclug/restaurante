package br.com.fiap.restaurante.infrastructure.gateways;

import br.com.fiap.restaurante.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.infrastructure.persistence.entities.ItemCardapioEntity;
import br.com.fiap.restaurante.infrastructure.persistence.repositories.ItemCardapioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemCardapioRepositoryGatewayTest {
    @Mock
    private ItemCardapioRepository itemCardapioRepository;

    @InjectMocks
    private ItemCardapioRepositoryGateway gateway;

    @Test
    void deveSalvarItemCardapio() {
        ItemCardapio item = ItemCardapio.builder().id(1L).nome("Pizza").build();
        ItemCardapioEntity entity = ItemCardapioEntity.builder().id(1L).nome("Pizza").build();

        when(itemCardapioRepository.save(any())).thenReturn(entity);

        ItemCardapio result = gateway.salvarItemCardapio(item);

        assertNotNull(result);
        assertEquals(item.getNome(), result.getNome());
        verify(itemCardapioRepository).save(any());
    }

    @Test
    void deveRemoverItemCardapio() {
        gateway.removerItemCardapio(5L);
        verify(itemCardapioRepository).deleteAllById(eq(List.of(5L)));
    }

    @Test
    void deveListarItensCardapio() {
        ItemCardapioEntity entity = ItemCardapioEntity.builder().id(2L).nome("Lasanha").build();
        when(itemCardapioRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(entity)));

        List<ItemCardapio> itens = gateway.listarItensCardapio(0, 10);

        assertNotNull(itens);
        assertEquals(1, itens.size());
        assertEquals("Lasanha", itens.get(0).getNome());
        verify(itemCardapioRepository).findAll(any(Pageable.class));
    }

    @Test
    void deveConsultarItemCardapioPorId() {
        ItemCardapioEntity entity = ItemCardapioEntity.builder().id(3L).nome("Risoto").build();
        when(itemCardapioRepository.findById(3L)).thenReturn(Optional.of(entity));

        Optional<ItemCardapio> opt = gateway.consultarItemCardapioPorId(3L);

        assertTrue(opt.isPresent());
        assertEquals("Risoto", opt.get().getNome());
        verify(itemCardapioRepository).findById(3L);
    }

    @Test
    void deveRetornarVazioQuandoNaoEncontrarPorId() {
        when(itemCardapioRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<ItemCardapio> opt = gateway.consultarItemCardapioPorId(99L);

        assertTrue(opt.isEmpty());
        verify(itemCardapioRepository).findById(99L);
    }
}