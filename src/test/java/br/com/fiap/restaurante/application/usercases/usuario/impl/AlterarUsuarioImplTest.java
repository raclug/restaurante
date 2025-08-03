package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.domain.entities.Endereco;
import br.com.fiap.restaurante.domain.entities.Senha;
import br.com.fiap.restaurante.domain.entities.UfEnum;
import br.com.fiap.restaurante.domain.entities.Usuario;
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
class AlterarUsuarioImplTest {
    @Mock
    private UsuarioPort usuarioPort;

    @InjectMocks
    private AlterarUsuarioImpl useCase;

    @Test
    void deveAlterarUsuarioComSucesso() {
        Long id = 1L;
        Endereco enderecoNovo = new Endereco(10L, "Rua Nova", "123", "Apto 1", "Centro", "Cidade", UfEnum.AC, "12345-678");
        Usuario usuarioNovo = Usuario.builder()
                .id(id)
                .nome("Novo Nome")
                .email("novo@email.com")
                .endereco(enderecoNovo)
                .build();

        Endereco enderecoAntigo = new Endereco(10L, "Rua Antiga", "321", "Casa", "Bairro", "Cidade", UfEnum.AC, "87654-321");
        Usuario usuarioAntigo = Usuario.builder()
                .id(id)
                .login("login")
                .senha(new Senha(1L, 1L, "senha"))
                .tipoUsuario(null)
                .endereco(enderecoAntigo)
                .build();

        when(usuarioPort.consultarUsuarioPorId(id)).thenReturn(Optional.of(usuarioAntigo));
        when(usuarioPort.salvarUsuario(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario resultado = useCase.execute(id, usuarioNovo);

        assertNotNull(resultado);
        assertEquals("Novo Nome", resultado.getNome());
        assertEquals("novo@email.com", resultado.getEmail());
        assertEquals("login", resultado.getLogin());
        assertEquals("senha", resultado.getSenha().getSenha());
        assertEquals("Rua Nova", resultado.getEndereco().logradouro());
        verify(usuarioPort).consultarUsuarioPorId(id);
        verify(usuarioPort).salvarUsuario(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        Long id = 2L;
        Usuario usuario = Usuario.builder().id(id).build();

        when(usuarioPort.consultarUsuarioPorId(id)).thenReturn(Optional.empty());

        RegistroNaoEncontradoException ex = assertThrows(
                RegistroNaoEncontradoException.class,
                () -> useCase.execute(id, usuario)
        );
        assertEquals("Usuário não encontrado.", ex.getMessage());
        verify(usuarioPort, never()).salvarUsuario(any());
    }
}