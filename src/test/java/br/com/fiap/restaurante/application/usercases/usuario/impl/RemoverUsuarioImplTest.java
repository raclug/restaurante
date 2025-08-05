package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoverUsuarioImplTest {


    @Mock
    private UsuarioPort usuarioPort;

    @InjectMocks
    private RemoverUsuarioImpl useCase;

    @Test
    void deveRemoverUsuarioComSucesso() {
        when(usuarioPort.consultarUsuarioPorId(1L)).thenReturn(Optional.of(Usuario.builder().id(1L).build()));

        useCase.execute(1L);

        verify(usuarioPort).consultarUsuarioPorId(1L);
        verify(usuarioPort).removerUsuario(1L);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        when(usuarioPort.consultarUsuarioPorId(2L)).thenReturn(Optional.empty());

        RegistroNaoEncontradoException ex = assertThrows(
                RegistroNaoEncontradoException.class,
                () -> useCase.execute(2L)
        );
        assertEquals("Usuário não encontrado.", ex.getMessage());
        verify(usuarioPort).consultarUsuarioPorId(2L);
        verify(usuarioPort, never()).removerUsuario(anyLong());
    }
}