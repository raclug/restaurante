package br.com.fiap.restaurante.infrastructure.gateways;

import br.com.fiap.restaurante.domain.entities.Senha;
import br.com.fiap.restaurante.infrastructure.persistence.entities.SenhaEntity;
import br.com.fiap.restaurante.infrastructure.persistence.entities.UsuarioEntity;
import br.com.fiap.restaurante.infrastructure.persistence.repositories.SenhaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SenhaRepositoryGatewayTest {
    @Mock
    private SenhaRepository senhaRepository;

    @InjectMocks
    private SenhaRepositoryGateway gateway;

    @Test
    void deveConsultarSenhaPorUsuarioId() {
        SenhaEntity entity = SenhaEntity.builder().id(1L).usuario(UsuarioEntity.builder().id(2L).build()).senha("hash").build();
        when(senhaRepository.findByUsuarioId(2L)).thenReturn(Optional.of(entity));

        Optional<Senha> opt = gateway.consultarSenhaPorUsuarioId(2L);

        assertTrue(opt.isPresent());
        assertEquals(2L, opt.get().getUsuarioId());
        assertEquals("hash", opt.get().getSenha());
        verify(senhaRepository).findByUsuarioId(2L);
    }

    @Test
    void deveRetornarVazioQuandoNaoEncontrarPorUsuarioId() {
        when(senhaRepository.findByUsuarioId(99L)).thenReturn(Optional.empty());

        Optional<Senha> opt = gateway.consultarSenhaPorUsuarioId(99L);

        assertTrue(opt.isEmpty());
        verify(senhaRepository).findByUsuarioId(99L);
    }

    @Test
    void deveConsultarSenhaPorLogin() {
        SenhaEntity entity = SenhaEntity.builder().id(3L).usuario(UsuarioEntity.builder().id(4L).build()).senha("hash2").build();
        when(senhaRepository.findByUsuarioLogin("user")).thenReturn(Optional.of(entity));

        Optional<Senha> opt = gateway.consultarSenhaPorLogin("user");

        assertTrue(opt.isPresent());
        assertEquals(4L, opt.get().getUsuarioId());
        assertEquals("hash2", opt.get().getSenha());
        verify(senhaRepository).findByUsuarioLogin("user");
    }

    @Test
    void deveRetornarVazioQuandoNaoEncontrarPorLogin() {
        when(senhaRepository.findByUsuarioLogin("naoexiste")).thenReturn(Optional.empty());

        Optional<Senha> opt = gateway.consultarSenhaPorLogin("naoexiste");

        assertTrue(opt.isEmpty());
        verify(senhaRepository).findByUsuarioLogin("naoexiste");
    }

    @Test
    void deveSalvarSenha() {
        Senha senha = new Senha(5L, 6L, "novaHash");
        when(senhaRepository.save(any())).thenReturn(SenhaEntity.builder().id(5L).usuario(UsuarioEntity.builder().id(6L).build()).senha("novaHash").build());

        gateway.salvarSenha(senha);

        verify(senhaRepository).save(any());
    }
}