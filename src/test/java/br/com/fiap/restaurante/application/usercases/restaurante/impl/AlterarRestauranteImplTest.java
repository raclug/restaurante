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

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AlterarRestauranteImplTest {
    @Mock
    private RestaurantePort restaurantePort;

    @Mock
    private UsuarioPort usuarioPort;

    @InjectMocks
    private AlterarRestauranteImpl alterarRestaurante;

    @Test
    void deveAlterarRestauranteComSucesso() {
        Long id = 1L;
        Long idResponsavel = 2L;
        Endereco endereco = new Endereco(10L,
                "Rua",
                "123",
                "Comp",
                "Bairro",
                "Cidade",
                UfEnum.AC,
                "00000-000");

        Restaurante restauranteInput = new Restaurante(null,
                "Novo Nome",
                endereco,
                "Italiana",
                "10:00",
                "22:00",
                idResponsavel);

        Restaurante restauranteExistente = new Restaurante(
                id,
                "Antigo Nome",
                endereco,
                "Italiana", "09:00", "21:00", idResponsavel);

        TipoUsuario tipoUsuario = new TipoUsuario(1L, "Dono de Restaurante");

        Usuario usuario = new Usuario(idResponsavel, "nome", "email", "login", null, tipoUsuario, endereco);

        when(restaurantePort.consultarRestaurantePorId(id)).thenReturn(Optional.of(restauranteExistente));
        when(usuarioPort.consultarUsuarioPorId(idResponsavel)).thenReturn(Optional.of(usuario));
        when(restaurantePort.salvarRestaurante(any(Restaurante.class))).thenAnswer(i -> i.getArgument(0));

        Restaurante resultado = alterarRestaurante.execute(id, restauranteInput);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Novo Nome", resultado.getNome());
        assertEquals("Italiana", resultado.getTipoCozinha());
        assertEquals(idResponsavel, resultado.getIdResponsavel());
        verify(restaurantePort).consultarRestaurantePorId(id);
        verify(usuarioPort).consultarUsuarioPorId(idResponsavel);
        verify(restaurantePort).salvarRestaurante(any(Restaurante.class));
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        Long id = 1L;
        Restaurante restaurante = mock(Restaurante.class);
        when(restaurantePort.consultarRestaurantePorId(id)).thenReturn(Optional.empty());

        RegistroNaoEncontradoException ex = assertThrows(
                RegistroNaoEncontradoException.class,
                () -> alterarRestaurante.execute(id, restaurante)
        );
        assertEquals("Restaurante não encontrado.", ex.getMessage());
        verify(restaurantePort).consultarRestaurantePorId(id);
        verifyNoInteractions(usuarioPort);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        Long id = 1L;
        Long idResponsavel = 2L;
        Endereco endereco = mock(Endereco.class);
        Restaurante restaurante = new Restaurante(
                null,
                "Nome",
                endereco,
                "Italiana",
                "10:00",
                "22:00",
                idResponsavel);
        Restaurante restauranteExistente = mock(Restaurante.class);

        when(restaurantePort.consultarRestaurantePorId(id)).thenReturn(Optional.of(restauranteExistente));
        when(usuarioPort.consultarUsuarioPorId(idResponsavel)).thenReturn(Optional.empty());

        RegistroNaoEncontradoException ex = assertThrows(
                RegistroNaoEncontradoException.class,
                () -> alterarRestaurante.execute(id, restaurante)
        );
        assertEquals("Usuário não encontrado.", ex.getMessage());
        verify(restaurantePort).consultarRestaurantePorId(id);
        verify(usuarioPort).consultarUsuarioPorId(idResponsavel);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoForDonoDeRestaurante() {
        Long id = 1L;
        Long idResponsavel = 2L;
        Endereco endereco = mock(Endereco.class);
        Restaurante restaurante = new Restaurante(
                null,
                "Nome",
                endereco,
                "Italiana",
                "10:00",
                "22:00",
                idResponsavel);

        Restaurante restauranteExistente = mock(Restaurante.class);

        TipoUsuario tipoUsuario = new TipoUsuario(1L, "Cliente");
        Usuario usuario = new Usuario(idResponsavel, "nome", "email", "login", null, tipoUsuario, endereco);

        when(restaurantePort.consultarRestaurantePorId(id)).thenReturn(Optional.of(restauranteExistente));
        when(usuarioPort.consultarUsuarioPorId(idResponsavel)).thenReturn(Optional.of(usuario));

        TipoUsuarioException ex = assertThrows(
                TipoUsuarioException.class,
                () -> alterarRestaurante.execute(id, restaurante)
        );
        assertEquals("Somente usuário do tipo 'Dono de Restaurante' pode ser responsável do restaurante.", ex.getMessage());
        verify(restaurantePort).consultarRestaurantePorId(id);
        verify(usuarioPort).consultarUsuarioPorId(idResponsavel);
    }
}