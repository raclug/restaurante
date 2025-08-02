package br.com.fiap.restaurante.main;

import br.com.fiap.restaurante.application.ports.PasswordEncoderPort;
import br.com.fiap.restaurante.application.ports.SenhaPort;
import br.com.fiap.restaurante.application.usercases.senha.SalvarSenha;
import br.com.fiap.restaurante.application.usercases.senha.impl.SalvarSenhaImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenhaConfig {

    @Bean
    public SalvarSenha salvarSenha(final SenhaPort senhaPort, final PasswordEncoderPort passwordEncoderPort) {
        return new SalvarSenhaImpl(passwordEncoderPort, senhaPort);
    }
}
