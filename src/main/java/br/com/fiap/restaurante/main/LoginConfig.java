package br.com.fiap.restaurante.main;

import br.com.fiap.restaurante.application.ports.PasswordEncoderPort;
import br.com.fiap.restaurante.application.ports.SenhaPort;
import br.com.fiap.restaurante.application.usercases.login.EfetuarLogin;
import br.com.fiap.restaurante.application.usercases.login.impl.EfetuarLoginImpl;
import br.com.fiap.restaurante.domain.entities.Login;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig {

    @Bean
    public EfetuarLogin efetuarLogin(final SenhaPort senhaPort, final PasswordEncoderPort passwordEncoderPort) {
        return new EfetuarLoginImpl(senhaPort, passwordEncoderPort);
    }
}
