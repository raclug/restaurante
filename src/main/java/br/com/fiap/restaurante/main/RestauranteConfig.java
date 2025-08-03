package br.com.fiap.restaurante.main;

import br.com.fiap.restaurante.application.ports.RestaurantePort;
import br.com.fiap.restaurante.application.ports.TipoUsuarioPort;
import br.com.fiap.restaurante.application.usercases.restaurante.*;
import br.com.fiap.restaurante.application.usercases.restaurante.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfig {

    @Bean
    public CriarRestaurante criarRestaurante(final RestaurantePort restaurantePort,
                                             final TipoUsuarioPort tipoUsuarioPort) {
        return new CriarRestauranteImpl(restaurantePort, tipoUsuarioPort);
    }

    @Bean
    public AlterarRestaurante alterarRestaurante(final RestaurantePort restaurantePort,
                                                 final TipoUsuarioPort tipoUsuarioPort) {
        return new AlterarRestauranteImpl(restaurantePort, tipoUsuarioPort);
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
