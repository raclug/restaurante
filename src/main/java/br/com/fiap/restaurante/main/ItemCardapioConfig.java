package br.com.fiap.restaurante.main;

import br.com.fiap.restaurante.application.ports.ItemCardapioPort;
import br.com.fiap.restaurante.application.usercases.ItemCardapio.*;
import br.com.fiap.restaurante.application.usercases.ItemCardapio.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemCardapioConfig {

    @Bean
    public AlterarItemCardapio alterarItemCardapio(final ItemCardapioPort itemCardapioPort) {
        return new AlterarItemCardapioImpl(itemCardapioPort);
    }

    @Bean
    public ConsultarItemCardapio consultarItemCardapio(final ItemCardapioPort itemCardapioPort) {
        return new ConsultarItemCardapioImpl(itemCardapioPort);
    }

    @Bean
    public CriarItemCardapio criarItemCardapio(final ItemCardapioPort itemCardapioPort) {
        return new CriarItemCardapioImpl(itemCardapioPort);
    }

    @Bean
    public ListarItensCardapio listarItensCardapio(final ItemCardapioPort itemCardapioPort) {
        return new ListarItensCardapioImpl(itemCardapioPort);
    }

    @Bean
    public RemoverItemCardapio removerItemCardapio(final ItemCardapioPort itemCardapioPort) {
        return new RemoverItemCardapioImpl(itemCardapioPort);
    }
}
