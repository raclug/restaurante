package br.com.fiap.restaurante.application.usercases.login.impl;

import br.com.fiap.restaurante.application.ports.PasswordEncoderPort;
import br.com.fiap.restaurante.application.ports.SenhaPort;
import br.com.fiap.restaurante.domain.entities.Login;
import br.com.fiap.restaurante.domain.entities.Senha;
import br.com.fiap.restaurante.domain.exceptions.NaoAutorizadoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EfetuarLoginImplTest {

    @Mock
    private SenhaPort senhaPort;

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @InjectMocks
    private EfetuarLoginImpl efetuarLogin;

    @Test
    void deveLancarExcecaoQuandoLoginNaoExiste() {
        Login login = new Login("senha", "usuario");

        when(senhaPort.consultarSenhaPorLogin("usuario")).thenReturn(Optional.empty());

        assertThrows(NaoAutorizadoException.class, () -> efetuarLogin.execute(login));
        verify(senhaPort).consultarSenhaPorLogin("usuario");
        verifyNoInteractions(passwordEncoderPort);
    }

    @Test
    void deveExecutarSemExcecoes() {
        Login login = new Login("senha", "usuario");
        Senha senhaCadastrada = new Senha(null,1L, "senha");

        when(senhaPort.consultarSenhaPorLogin("usuario")).thenReturn(Optional.of(senhaCadastrada));
        when(passwordEncoderPort.matches("senha", "senha")).thenReturn(true);

        efetuarLogin.execute(login);

        verify(senhaPort).consultarSenhaPorLogin("usuario");
        verify(passwordEncoderPort).matches("senha", "senha");
    }

    @Test
    void deveLancarExcecaoQuandoSenhaNaoConfere() {
        Login login = new Login("senha", "usuario");
        Senha senhaCadastrada = new Senha(null,1L, "senhaCriptografada");
        when(senhaPort.consultarSenhaPorLogin("usuario")).thenReturn(Optional.of(senhaCadastrada));
        when(passwordEncoderPort.matches("senha", "senhaCriptografada")).thenReturn(false);

        assertThrows(NaoAutorizadoException.class, () -> efetuarLogin.execute(login));
        verify(senhaPort).consultarSenhaPorLogin("usuario");
        verify(passwordEncoderPort).matches("senha", "senhaCriptografada");
    }
}