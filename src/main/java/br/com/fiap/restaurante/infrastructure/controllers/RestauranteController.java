package br.com.fiap.restaurante.infrastructure.controllers;


import br.com.fiap.restaurante.infrastructure.controllers.interfaces.IRestauranteController;
import br.com.fiap.restaurante.infrastructure.dtos.PaginaDTO;
import br.com.fiap.restaurante.infrastructure.dtos.TipoUsuarioDTO;
import br.com.fiap.restaurante.infrastructure.mappers.TipoUsuarioDTOMapper;
import br.com.fiap.restaurante.infrastructure.services.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/restaurantes")
@AllArgsConstructor
public class RestauranteController implements IRestauranteController {

    private final RestauranteService restauranteService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TipoUsuarioDTO criarTipoUsuario(@RequestBody @Validated final TipoUsuarioDTO tipoUsuarioDTO) {
        return toDTO(tipoUsuarioService.criarTipoUsuario(toDomain(tipoUsuarioDTO)));
    }

    @PutMapping("/{id}")
    public TipoUsuarioDTO alterarTipoUsuario(@RequestBody @Validated final TipoUsuarioDTO tipoUsuarioDTO,
                                             @PathVariable final Long id) {

        return toDTO(tipoUsuarioService.alterarTipoUsuario(id, toDomain(tipoUsuarioDTO)));
    }

    @GetMapping
    public List<TipoUsuarioDTO> listarTiposUsuario(final PaginaDTO paginaDTO) {
        return tipoUsuarioService.listarTiposUsuario(paginaDTO.getPagina(), paginaDTO.getTamanhoPagina())
                .stream()
                .map(TipoUsuarioDTOMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public TipoUsuarioDTO consultarTipoUsuario(@PathVariable final Long id) {
        return toDTO(tipoUsuarioService.consultarTipoUsuario(id));
    }

    @DeleteMapping("/{id}")
    public void removerTipoUsuario(@PathVariable final Long id) {
        tipoUsuarioService.removerTipoUsuario(id);
    }
}
