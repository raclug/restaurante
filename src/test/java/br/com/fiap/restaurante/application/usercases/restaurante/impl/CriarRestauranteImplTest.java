package br.com.fiap.restaurante.application.usercases.restaurante.impl;

import br.com.fiap.restaurante.application.ports.RestaurantePort;
import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.domain.entities.*;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import br.com.fiap.restaurante.domain.exceptions.TipoUsuarioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class CriarRestauranteImplTest {

    @Mock
    private RestaurantePort restaurantePort;

    @Mock
    private UsuarioPort usuarioPort;

    @InjectMocks
    private CriarRestauranteImpl criarRestaurante;

    @Test
    void deveCriarRestauranteComSucesso() {
        Long idResponsavel = 2L;
        Restaurante restaurante = new Restaurante();
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "Dono de Restaurante");
        Usuario usuario = new Usuario();
        usuario.setTipoUsuario(tipoUsuario);
        restaurante.setIdResponsavel(idResponsavel);

        when(usuarioPort.consultarUsuarioPorId(idResponsavel)).thenReturn(Optional.of(usuario));
        when(restaurantePort.salvarRestaurante(any(Restaurante.class))).thenAnswer(i -> i.getArgument(0));

        criarRestaurante.execute(restaurante);

        verify(usuarioPort).consultarUsuarioPorId(idResponsavel);
        verify(restaurantePort).salvarRestaurante(restaurante);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        Long idResponsavel = 2L;
        Restaurante restaurante = new Restaurante();
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "Dono de Restaurante");
        Usuario usuario = new Usuario();
        usuario.setTipoUsuario(tipoUsuario);
        restaurante.setIdResponsavel(idResponsavel);

        when(usuarioPort.consultarUsuarioPorId(idResponsavel)).thenReturn(Optional.empty());

        RegistroNaoEncontradoException ex = assertThrows(
                RegistroNaoEncontradoException.class,
                () -> criarRestaurante.execute(restaurante)
        );
        assertEquals("Usuário não encontrado.", ex.getMessage());
        verify(usuarioPort).consultarUsuarioPorId(idResponsavel);
        verifyNoInteractions(restaurantePort);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoForDonoDeRestaurante() {
        Long idResponsavel = 2L;
        Restaurante restaurante = new Restaurante();
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "Usuário");
        Usuario usuario = new Usuario();
        usuario.setTipoUsuario(tipoUsuario);
        restaurante.setIdResponsavel(idResponsavel);

        when(usuarioPort.consultarUsuarioPorId(idResponsavel)).thenReturn(Optional.of(usuario));

        TipoUsuarioException ex = assertThrows(
                TipoUsuarioException.class,
                () -> criarRestaurante.execute(restaurante)
        );
        assertEquals("Somente usuário do tipo 'Dono de Restaurante' pode ser responsável do restaurante.", ex.getMessage());
        verify(usuarioPort).consultarUsuarioPorId(idResponsavel);
        verifyNoInteractions(restaurantePort);
    }
}