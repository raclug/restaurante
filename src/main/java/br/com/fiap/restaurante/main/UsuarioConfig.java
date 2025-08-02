package br.com.fiap.restaurante.main;

import br.com.fiap.restaurante.application.ports.PasswordEncoderPort;
import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.application.usercases.usuario.*;
import br.com.fiap.restaurante.application.usercases.usuario.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {

    @Bean
    public AlterarUsuario alterarUsuario(final UsuarioPort usuarioPort) {
        return new AlterarUsuarioImpl(usuarioPort);
    }

    @Bean
    public ConsultarUsuario consultarUsuario(final UsuarioPort usuarioPort) {
        return new ConsultarUsuarioImpl(usuarioPort);
    }

    @Bean
    public CriarUsuario criarUsuario(final UsuarioPort usuarioPort, final PasswordEncoderPort passwordEncoderPort) {
        return new CriarUsuarioImpl(usuarioPort, passwordEncoderPort);
    }

    @Bean
    public ListarUsuarios listarUsuarios(final UsuarioPort usuarioPort) {
        return new ListarUsuariosImpl(usuarioPort);
    }

    @Bean
    public RemoverUsuario removerUsuario(final UsuarioPort usuarioPort) {
        return new RemoverUsuarioImpl(usuarioPort);
    }
}
