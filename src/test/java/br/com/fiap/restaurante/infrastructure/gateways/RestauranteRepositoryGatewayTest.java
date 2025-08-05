package br.com.fiap.restaurante.infrastructure.gateways;

import br.com.fiap.restaurante.domain.entities.Endereco;
import br.com.fiap.restaurante.domain.entities.Restaurante;
import br.com.fiap.restaurante.infrastructure.persistence.entities.EnderecoEntity;
import br.com.fiap.restaurante.infrastructure.persistence.entities.RestauranteEntity;
import br.com.fiap.restaurante.infrastructure.persistence.entities.UsuarioEntity;
import br.com.fiap.restaurante.infrastructure.persistence.repositories.RestauranteRepository;
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
class RestauranteRepositoryGatewayTest {
    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private RestauranteRepositoryGateway gateway;

    @Test
    void deveSalvarRestaurante() {
        Endereco endereco = new Endereco(1L, "Rua A", "123", "Apto 1", "Bairro A", "Cidade A", null, "12345-678");
        Restaurante restaurante = Restaurante.builder().id(1L).nome("Restaurante A").endereco(endereco).build();
        RestauranteEntity entity = RestauranteEntity.builder().id(1L).nome("Restaurante A").responsavel(new UsuarioEntity()).endereco(new EnderecoEntity()).build();

        when(restauranteRepository.save(any())).thenReturn(entity);

        Restaurante result = gateway.salvarRestaurante(restaurante);

        assertNotNull(result);
        assertEquals(restaurante.getNome(), result.getNome());
        verify(restauranteRepository).save(any());
    }

    @Test
    void deveRemoverRestaurante() {
        gateway.removerRestaurante(5L);
        verify(restauranteRepository).deleteAllById(eq(List.of(5L)));
    }

    @Test
    void deveListarRestaurantes() {
        RestauranteEntity entity = RestauranteEntity.builder().id(2L).nome("Restaurante B").responsavel(new UsuarioEntity()).endereco(new EnderecoEntity()).build();
        when(restauranteRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(entity)));

        List<Restaurante> restaurantes = gateway.listarRestaurante(0, 10);

        assertNotNull(restaurantes);
        assertEquals(1, restaurantes.size());
        assertEquals("Restaurante B", restaurantes.get(0).getNome());
        verify(restauranteRepository).findAll(any(Pageable.class));
    }

    @Test
    void deveConsultarRestaurantePorId() {
        RestauranteEntity entity = RestauranteEntity.builder().id(3L).nome("Restaurante C").responsavel(new UsuarioEntity()).endereco(new EnderecoEntity()).build();
        when(restauranteRepository.findById(3L)).thenReturn(Optional.of(entity));

        Optional<Restaurante> opt = gateway.consultarRestaurantePorId(3L);

        assertTrue(opt.isPresent());
        assertEquals("Restaurante C", opt.get().getNome());
        verify(restauranteRepository).findById(3L);
    }

    @Test
    void deveRetornarVazioQuandoNaoEncontrarPorId() {
        when(restauranteRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Restaurante> opt = gateway.consultarRestaurantePorId(99L);

        assertTrue(opt.isEmpty());
        verify(restauranteRepository).findById(99L);
    }
}