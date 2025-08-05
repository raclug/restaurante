package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.ports.PasswordEncoderPort;
import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.domain.entities.Senha;
import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.domain.exceptions.LoginJaExistenteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarUsuarioImplTest {
    @Mock
    private UsuarioPort usuarioPort;

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @InjectMocks
    private CriarUsuarioImpl useCase;

    @Test
    void deveCriarUsuarioComSucesso() {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .login("novo")
                .senha(new Senha(1L, 1L, "senha123"))
                .build();

        when(usuarioPort.consultarUsuarioPorLogin("novo")).thenReturn(Optional.empty());
        when(passwordEncoderPort.encode("senha123")).thenReturn("senhaCodificada");
        when(usuarioPort.salvarUsuario(usuario)).thenReturn(usuario);

        Usuario resultado = useCase.execute(usuario);

        assertNotNull(resultado);
        assertEquals("novo", resultado.getLogin());
        assertEquals("senhaCodificada", usuario.getSenha().getSenha());
        verify(usuarioPort).consultarUsuarioPorLogin("novo");
        verify(passwordEncoderPort).encode("senha123");
        verify(usuarioPort).salvarUsuario(usuario);
    }

    @Test
    void deveLancarExcecaoQuandoLoginJaExistente() {
        Usuario usuario = Usuario.builder()
                .id(2L)
                .login("existente")
                .senha(new Senha(1L, 1L, "senha"))
                .build();

        when(usuarioPort.consultarUsuarioPorLogin("existente")).thenReturn(Optional.of(usuario));

        LoginJaExistenteException ex = assertThrows(
                LoginJaExistenteException.class,
                () -> useCase.execute(usuario)
        );
        assertEquals("Login já está sendo utilizado.", ex.getMessage());
        verify(usuarioPort).consultarUsuarioPorLogin("existente");
        verify(passwordEncoderPort, never()).encode(anyString());
        verify(usuarioPort, never()).salvarUsuario(any());
    }
}