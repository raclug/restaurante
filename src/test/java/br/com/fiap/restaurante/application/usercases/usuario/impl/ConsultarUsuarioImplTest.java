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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarUsuarioImplTest {
    @Mock
    private UsuarioPort usuarioPort;

    @InjectMocks
    private ConsultarUsuarioImpl useCase;

    @Test
    void deveConsultarUsuarioComSucesso() {
        Usuario usuario = Usuario.builder().id(1L).login("user").build();
        when(usuarioPort.consultarUsuarioPorId(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = useCase.execute(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("user", resultado.getLogin());
        verify(usuarioPort).consultarUsuarioPorId(1L);
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
    }
}