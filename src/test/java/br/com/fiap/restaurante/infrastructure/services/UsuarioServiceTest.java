package br.com.fiap.restaurante.infrastructure.services;

import br.com.fiap.restaurante.application.usercases.usuario.*;
import br.com.fiap.restaurante.domain.entities.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @Mock
    private CriarUsuario criarUsuario;
    @Mock
    private AlterarUsuario alterarUsuario;
    @Mock
    private ListarUsuarios listarUsuarios;
    @Mock
    private ConsultarUsuario consultarUsuario;
    @Mock
    private RemoverUsuario removerUsuario;
    @Mock
    private AdicionarTipoUsuario adicionarTipoUsuario;

    @InjectMocks
    private UsuarioService service;

    @Test
    void deveCriarUsuario() {
        Usuario usuario = Usuario.builder().id(1L).login("user").build();
        when(criarUsuario.execute(usuario)).thenReturn(usuario);

        Usuario result = service.criarUsuario(usuario);

        assertNotNull(result);
        assertEquals("user", result.getLogin());
        verify(criarUsuario).execute(usuario);
    }

    @Test
    void deveAlterarUsuario() {
        Usuario usuario = Usuario.builder().id(2L).login("user2").build();
        when(alterarUsuario.execute(2L, usuario)).thenReturn(usuario);

        Usuario result = service.alterarUsario(2L, usuario);

        assertNotNull(result);
        assertEquals("user2", result.getLogin());
        verify(alterarUsuario).execute(2L, usuario);
    }

    @Test
    void deveListarUsuarios() {
        Usuario usuario = Usuario.builder().id(3L).login("user3").build();
        when(listarUsuarios.execute(0, 10)).thenReturn(List.of(usuario));

        List<Usuario> usuarios = service.listarUsuarios(0, 10);

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals("user3", usuarios.get(0).getLogin());
        verify(listarUsuarios).execute(0, 10);
    }

    @Test
    void deveConsultarUsuario() {
        Usuario usuario = Usuario.builder().id(4L).login("user4").build();
        when(consultarUsuario.execute(4L)).thenReturn(usuario);

        Usuario result = service.consultarUsuario(4L);

        assertNotNull(result);
        assertEquals("user4", result.getLogin());
        verify(consultarUsuario).execute(4L);
    }

    @Test
    void deveRemoverUsuario() {
        service.removerUsuario(5L);
        verify(removerUsuario).execute(5L);
    }

    @Test
    void deveAdicionarTipoUsuario() {
        service.adicionarTipoUsuario(6L, 7L);
        verify(adicionarTipoUsuario).execute(6L, 7L);
    }
}