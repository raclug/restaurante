package br.com.fiap.restaurante.application.usercases.senha.impl;

import br.com.fiap.restaurante.application.ports.PasswordEncoderPort;
import br.com.fiap.restaurante.application.ports.SenhaPort;
import br.com.fiap.restaurante.domain.entities.Senha;
import br.com.fiap.restaurante.domain.exceptions.NaoAutorizadoException;
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
class SalvarSenhaImplTest {
    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @Mock
    private SenhaPort senhaPort;

    @InjectMocks
    private SalvarSenhaImpl salvarSenha;

    @Test
    void deveAlterarSenhaComSucesso() {
        Long usuarioId = 1L;
        String senhaAtual = "senhaAntiga";
        String novaSenha = "senhaNova";
        Senha senhaCadastrada = new Senha(10L, usuarioId, "hashAntigo");

        when(senhaPort.consultarSenhaPorUsuarioId(usuarioId)).thenReturn(Optional.of(senhaCadastrada));
        when(passwordEncoderPort.matches(senhaAtual, senhaCadastrada.getSenha())).thenReturn(false);
        when(passwordEncoderPort.encode(novaSenha)).thenReturn("hashNova");

        assertDoesNotThrow(() -> salvarSenha.execute(usuarioId, senhaAtual, novaSenha));
        verify(senhaPort).consultarSenhaPorUsuarioId(usuarioId);
        verify(passwordEncoderPort).matches(senhaAtual, senhaCadastrada.getSenha());
        verify(passwordEncoderPort).encode(novaSenha);
        verify(senhaPort).salvarSenha(any(Senha.class));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        Long usuarioId = 2L;
        when(senhaPort.consultarSenhaPorUsuarioId(usuarioId)).thenReturn(Optional.empty());

        RegistroNaoEncontradoException ex = assertThrows(
                RegistroNaoEncontradoException.class,
                () -> salvarSenha.execute(usuarioId, "qualquer", "nova")
        );
        assertEquals("Usuário não encontrado.", ex.getMessage());
        verify(senhaPort).consultarSenhaPorUsuarioId(usuarioId);
        verifyNoInteractions(passwordEncoderPort);
    }

    @Test
    void deveLancarExcecaoQuandoSenhaAtualInvalida() {
        Long usuarioId = 3L;
        String senhaAtual = "errada";
        Senha senhaCadastrada = new Senha(20L, usuarioId, "hash");

        when(senhaPort.consultarSenhaPorUsuarioId(usuarioId)).thenReturn(Optional.of(senhaCadastrada));
        when(passwordEncoderPort.matches(senhaAtual, senhaCadastrada.getSenha())).thenReturn(true);

        NaoAutorizadoException ex = assertThrows(
                NaoAutorizadoException.class,
                () -> salvarSenha.execute(usuarioId, senhaAtual, "nova")
        );
        assertEquals("Senha atual inválida", ex.getMessage());
        verify(senhaPort).consultarSenhaPorUsuarioId(usuarioId);
        verify(passwordEncoderPort).matches(senhaAtual, senhaCadastrada.getSenha());
        verify(passwordEncoderPort, never()).encode(any());
        verify(senhaPort, never()).salvarSenha(any());
    }
}