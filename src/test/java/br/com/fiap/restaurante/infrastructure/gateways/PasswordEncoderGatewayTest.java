package br.com.fiap.restaurante.infrastructure.gateways;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PasswordEncoderGatewayTest {
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PasswordEncoderGateway gateway;

    @Test
    void deveCodificarSenha() {
        String raw = "senha123";
        String encoded = "hashSenha";
        when(passwordEncoder.encode(raw)).thenReturn(encoded);

        String resultado = gateway.encode(raw);

        assertEquals(encoded, resultado);
        verify(passwordEncoder).encode(raw);
    }

    @Test
    void deveVerificarSeSenhasCorrespondem() {
        String raw = "senha123";
        String encoded = "hashSenha";
        when(passwordEncoder.matches(raw, encoded)).thenReturn(true);

        boolean resultado = gateway.matches(raw, encoded);

        assertTrue(resultado);
        verify(passwordEncoder).matches(raw, encoded);
    }
}