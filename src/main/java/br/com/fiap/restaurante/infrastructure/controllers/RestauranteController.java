package br.com.fiap.restaurante.infrastructure.controllers;


import br.com.fiap.restaurante.infrastructure.controllers.interfaces.IRestauranteController;
import br.com.fiap.restaurante.infrastructure.dtos.PaginaDTO;
import br.com.fiap.restaurante.infrastructure.dtos.RestauranteDTO;
import br.com.fiap.restaurante.infrastructure.mappers.RestauranteDTOMapper;
import br.com.fiap.restaurante.infrastructure.services.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fiap.restaurante.infrastructure.mappers.RestauranteDTOMapper.toDTO;
import static br.com.fiap.restaurante.infrastructure.mappers.RestauranteDTOMapper.toDomain;


@RestController
@RequestMapping("/v1/restaurantes")
@AllArgsConstructor
public class RestauranteController implements IRestauranteController {

    private final RestauranteService restauranteService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RestauranteDTO criarRestaurante(@RequestBody @Validated final RestauranteDTO restauranteDTO) {
        return toDTO(restauranteService.criarRestaurante(toDomain(restauranteDTO)));
    }

    @PutMapping("/{id}")
    public RestauranteDTO alterarRestaurante(@RequestBody @Validated final RestauranteDTO restauranteDTO,
                                             @PathVariable final Long id) {

        return toDTO(restauranteService.alterarRestaurante(id, toDomain(restauranteDTO)));
    }

    @GetMapping
    public List<RestauranteDTO> listarRestaurantes(final PaginaDTO paginaDTO) {
        return restauranteService.listarRestaurantes(paginaDTO.getPagina(), paginaDTO.getTamanhoPagina())
                .stream()
                .map(RestauranteDTOMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public RestauranteDTO consultarRestaurante(@PathVariable final Long id) {
        return toDTO(restauranteService.consultarRestaurante(id));
    }

    @DeleteMapping("/{id}")
    public void removerRestaurante(@PathVariable final Long id) {
        restauranteService.removerRestaurante(id);
    }
}
