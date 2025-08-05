package br.com.fiap.restaurante.infrastructure.gateways;

import br.com.fiap.restaurante.application.ports.ItemCardapioPort;
import br.com.fiap.restaurante.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.infrastructure.mappers.ItemCardapioEntityMapper;
import br.com.fiap.restaurante.infrastructure.persistence.repositories.ItemCardapioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.fiap.restaurante.infrastructure.mappers.ItemCardapioEntityMapper.toDomain;
import static br.com.fiap.restaurante.infrastructure.mappers.ItemCardapioEntityMapper.toEntity;
import static br.com.fiap.restaurante.infrastructure.mappers.PageableMapper.toPageable;

@Component
@AllArgsConstructor
public class ItemCardapioRepositoryGateway implements ItemCardapioPort {

    private final ItemCardapioRepository itemCardapioRepository;

    @Override
    public ItemCardapio salvarItemCardapio(final ItemCardapio itemCardapio) {

        final var itemCardapioEntity = itemCardapioRepository.save(toEntity(itemCardapio));

        return toDomain(itemCardapioEntity);
    }

    @Override
    public void removerItemCardapio(Long id) {
        itemCardapioRepository.deleteAllById(Collections.singletonList(id));
    }

    @Override
    public List<ItemCardapio> listarItensCardapio(Integer pagina, Integer tamanhoPagina) {

        var pageable = toPageable(pagina, tamanhoPagina);

        return itemCardapioRepository.findAll(pageable)
                .stream()
                .map(ItemCardapioEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<ItemCardapio> consultarItemCardapioPorId(Long id) {
        return itemCardapioRepository.findById(id).map(ItemCardapioEntityMapper::toDomain);
    }
}
