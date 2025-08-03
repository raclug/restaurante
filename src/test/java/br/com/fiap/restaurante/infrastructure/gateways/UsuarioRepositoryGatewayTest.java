package br.com.fiap.restaurante.infrastructure.gateways;

import br.com.fiap.restaurante.domain.entities.Endereco;
import br.com.fiap.restaurante.domain.entities.UfEnum;
import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.infrastructure.persistence.entities.EnderecoEntity;
import br.com.fiap.restaurante.infrastructure.persistence.entities.SenhaEntity;
import br.com.fiap.restaurante.infrastructure.persistence.entities.UsuarioEntity;
import br.com.fiap.restaurante.infrastructure.persistence.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioRepositoryGatewayTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioRepositoryGateway gateway;

    @Test
    void deveSalvarUsuario() {
        Endereco endereco = new Endereco(null, "Rua A", "123", "Bairro B", "Cidade C", "Estado D", UfEnum.AC, "12345-678");
        Usuario usuario = Usuario.builder().id(1L).login("user").endereco(endereco).build();
        UsuarioEntity entity = UsuarioEntity.builder().id(1L).login("user").senha(new SenhaEntity()).endereco(new EnderecoEntity()).build();

        when(usuarioRepository.save(any())).thenReturn(entity);

        Usuario result = gateway.salvarUsuario(usuario);

        assertNotNull(result);
        assertEquals(usuario.getLogin(), result.getLogin());
        verify(usuarioRepository).save(any());
    }

    @Test
    void deveRemoverUsuario() {
        gateway.removerUsuario(5L);
        verify(usuarioRepository).deleteAllById(eq(List.of(5L)));
    }

    @Test
    void deveListarUsuarios() {
        UsuarioEntity entity = UsuarioEntity.builder().id(2L).login("user2").senha(new SenhaEntity()).endereco(new EnderecoEntity()).build();
        when(usuarioRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(entity)));

        List<Usuario> usuarios = gateway.listarUsuarios(0, 10);

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals("user2", usuarios.get(0).getLogin());
        verify(usuarioRepository).findAll(any(Pageable.class));
    }

    @Test
    void deveConsultarUsuarioPorId() {
        UsuarioEntity entity = UsuarioEntity.builder().id(3L).login("user3").senha(new SenhaEntity()).endereco(new EnderecoEntity()).build();
        when(usuarioRepository.findById(3L)).thenReturn(Optional.of(entity));

        Optional<Usuario> opt = gateway.consultarUsuarioPorId(3L);

        assertTrue(opt.isPresent());
        assertEquals("user3", opt.get().getLogin());
        verify(usuarioRepository).findById(3L);
    }

    @Test
    void deveRetornarVazioQuandoNaoEncontrarPorId() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Usuario> opt = gateway.consultarUsuarioPorId(99L);

        assertTrue(opt.isEmpty());
        verify(usuarioRepository).findById(99L);
    }

    @Test
    void deveConsultarUsuarioPorLogin() {
        UsuarioEntity entity = UsuarioEntity.builder().id(4L).login("login4").senha(new SenhaEntity()).endereco(new EnderecoEntity()).build();
        when(usuarioRepository.findFirstByLogin("login4")).thenReturn(Optional.of(entity));

        Optional<Usuario> opt = gateway.consultarUsuarioPorLogin("login4");

        assertTrue(opt.isPresent());
        assertEquals("login4", opt.get().getLogin());
        verify(usuarioRepository).findFirstByLogin("login4");
    }

    @Test
    void deveRetornarVazioQuandoNaoEncontrarPorLogin() {
        when(usuarioRepository.findFirstByLogin("naoexiste")).thenReturn(Optional.empty());

        Optional<Usuario> opt = gateway.consultarUsuarioPorLogin("naoexiste");

        assertTrue(opt.isEmpty());
        verify(usuarioRepository).findFirstByLogin("naoexiste");
    }

    @Test
    void deveAdicionarTipoUsuario() {
        gateway.adicionarTipoUsuario(1L, 2L);
        verify(usuarioRepository).addTipoUsuario(1L, 2L);
    }
}