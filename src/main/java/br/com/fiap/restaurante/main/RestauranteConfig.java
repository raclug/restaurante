package br.com.fiap.restaurante.main;

import br.com.fiap.restaurante.application.ports.RestaurantePort;
import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.application.usercases.restaurante.*;
import br.com.fiap.restaurante.application.usercases.restaurante.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfig {

    @Bean
    public CriarRestaurante criarRestaurante(final RestaurantePort restaurantePort,
                                             final UsuarioPort usuarioPort) {
        return new CriarRestauranteImpl(restaurantePort, usuarioPort);
    }

    @Bean
    public AlterarRestaurante alterarRestaurante(final RestaurantePort restaurantePort,
                                                 final UsuarioPort usuarioPort) {
        return new AlterarRestauranteImpl(restaurantePort, usuarioPort);
    }

    @Bean
    public ConsultarRestaurante consultarRestaurante(final RestaurantePort restaurantePort) {
        return new ConsultarRestauranteImpl(restaurantePort);
    }

    @Bean
    public ListarRestaurante listarRestaurante(final RestaurantePort restaurantePort) {
        return new ListarRestauranteImpl(restaurantePort);
    }

    @Bean
    public RemoverRestaurante removerRestaurante(final RestaurantePort restaurantePort) {
        return new RemoverRestauranteImpl(restaurantePort);
    }
}
