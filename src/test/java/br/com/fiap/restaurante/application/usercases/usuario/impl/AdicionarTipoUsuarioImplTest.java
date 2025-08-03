package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.ports.TipoUsuarioPort;
import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.domain.entities.TipoUsuario;
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
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class AdicionarTipoUsuarioImplTest {
    @Mock
    private UsuarioPort usuarioPort;

    @Mock
    private TipoUsuarioPort tipoUsuarioPort;

    @InjectMocks
    private AdicionarTipoUsuarioImpl useCase;

    @Test
    void deveAdicionarTipoUsuarioComSucesso() {
        when(usuarioPort.consultarUsuarioPorId(1L)).thenReturn(Optional.of(Usuario.builder().id(1L).build()));
        when(tipoUsuarioPort.consultarTipoUsuarioPorId(2L)).thenReturn(Optional.of(TipoUsuario.builder().id(2L).build()));

        useCase.execute(1L, 2L);

        verify(usuarioPort).adicionarTipoUsuario(1L, 2L);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        when(usuarioPort.consultarUsuarioPorId(1L)).thenReturn(Optional.empty());

        RegistroNaoEncontradoException ex = assertThrows(
                RegistroNaoEncontradoException.class,
                () -> useCase.execute(1L, 2L)
        );
        assertEquals("Usuário não encontrado.", ex.getMessage());
        verify(usuarioPort, never()).adicionarTipoUsuario(anyLong(), anyLong());
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontrado() {
        when(usuarioPort.consultarUsuarioPorId(1L)).thenReturn(Optional.of(Usuario.builder().id(1L).build()));
        when(tipoUsuarioPort.consultarTipoUsuarioPorId(2L)).thenReturn(Optional.empty());

        RegistroNaoEncontradoException ex = assertThrows(
                RegistroNaoEncontradoException.class,
                () -> useCase.execute(1L, 2L)
        );
        assertEquals("Tipo de Usuário não encontrado.", ex.getMessage());
        verify(usuarioPort, never()).adicionarTipoUsuario(anyLong(), anyLong());
    }
}