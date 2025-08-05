package br.com.fiap.restaurante.main;

import br.com.fiap.restaurante.application.ports.TipoUsuarioPort;
import br.com.fiap.restaurante.application.usercases.tipousuario.*;
import br.com.fiap.restaurante.application.usercases.tipousuario.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TipoUsuarioConfig {

    @Bean
    public AlterarTipoUsuario alterarTipoUsuario(final TipoUsuarioPort tipoUsuarioPort) {
        return new AlterarTipoUsuarioImpl(tipoUsuarioPort);
    }

    @Bean
    public ConsultarTipoUsuario consultarTipoUsuario(final TipoUsuarioPort tipoUsuarioPort) {
        return new ConsultarTipoUsuarioImpl(tipoUsuarioPort);
    }

    @Bean
    public CriarTipoUsuario criarTipoUsuario(final TipoUsuarioPort tipoUsuarioPort) {
        return new CriarTipoUsuarioImpl(tipoUsuarioPort);
    }

    @Bean
    public ListarTiposUsuario listarTiposUsuario(final TipoUsuarioPort tipoUsuarioPort) {
        return new ListarTiposUsuarioImpl(tipoUsuarioPort);
    }

    @Bean
    public RemoverTipoUsuario removerTipoUsuario(final TipoUsuarioPort tipoUsuarioPort) {
        return new RemoverTipoUsuarioImpl(tipoUsuarioPort);
    }
}
