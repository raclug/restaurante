package br.com.fiap.restaurante.infrastructure.mappers;

import org.springframework.data.domain.Pageable;

public class PageableMapper {

    public static Pageable toPageable(int page, int size) {
        return Pageable.ofSize(size).withPage(page);
    }
}
