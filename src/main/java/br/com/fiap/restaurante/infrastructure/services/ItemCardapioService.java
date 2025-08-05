package br.com.fiap.restaurante.infrastructure.services;

import br.com.fiap.restaurante.application.usercases.ItemCardapio.*;
import br.com.fiap.restaurante.domain.entities.ItemCardapio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemCardapioService {

    private final CriarItemCardapio criarItemCardapio;

    private final AlterarItemCardapio alterarItemCardapio;

    private final ListarItensCardapio listarItensCardapio;

    private final ConsultarItemCardapio consultarItemCardapio;

    private final RemoverItemCardapio removerItemCardapio;

    @Transactional
    public ItemCardapio criarItemCardapio(final ItemCardapio itemCardapio) {
        return criarItemCardapio.execute(itemCardapio);
    }

    @Transactional
    public ItemCardapio alterarItemCardapio(final Long itemCardapioId, final ItemCardapio itemCardapio) {
        return alterarItemCardapio.execute(itemCardapioId, itemCardapio);
    }

    public ItemCardapio consultarItemCardapio(final Long itemCardapioId) {
        return consultarItemCardapio.execute(itemCardapioId);
    }

    @Transactional
    public void removerItemCardapio(final Long itemCardapioId) {
        removerItemCardapio.execute(itemCardapioId);
    }

    public List<ItemCardapio> listarItensCardapio(final Integer pagina, final Integer tamanhoPagina) {
        return listarItensCardapio.execute(pagina, tamanhoPagina);
    }
}
