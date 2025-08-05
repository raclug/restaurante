package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.ports.UsuarioPort;
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
class ListarUsuariosImplTest {
    @Mock
    private UsuarioPort usuarioPort;

    @InjectMocks
    private ListarUsuariosImpl useCase;

    @Test
    void deveListarUsuariosComSucesso() {
        List<Usuario> usuarios = List.of(
                Usuario.builder().id(1L).login("user1").build(),
                Usuario.builder().id(2L).login("user2").build()
        );

        when(usuarioPort.listarUsuarios(0, 10)).thenReturn(usuarios);

        List<Usuario> resultado = useCase.execute(0, 10);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("user1", resultado.get(0).getLogin());
        assertEquals("user2", resultado.get(1).getLogin());
        verify(usuarioPort).listarUsuarios(0, 10);
    }
}