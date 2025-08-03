package br.com.fiap.restaurante.infrastructure.controllers;


import br.com.fiap.restaurante.application.usercases.tipousuario.*;
import br.com.fiap.restaurante.infrastructure.controllers.interfaces.ITipoUsuarioController;
import br.com.fiap.restaurante.infrastructure.dtos.PaginaDTO;
import br.com.fiap.restaurante.infrastructure.dtos.TipoUsuarioDTO;
import br.com.fiap.restaurante.infrastructure.mappers.TipoUsuarioDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fiap.restaurante.infrastructure.mappers.TipoUsuarioDTOMapper.toDTO;
import static br.com.fiap.restaurante.infrastructure.mappers.TipoUsuarioDTOMapper.toDomain;

@RestController
@RequestMapping("/v1/tipos-usuario")
@AllArgsConstructor
public class TipoUsuarioController implements ITipoUsuarioController{

    private final CriarTipoUsuario criarTipoUsuario;

    private final AlterarTipoUsuario alterarTipoUsuario;

    private final ListarTiposUsuario listarTiposUsuario;

    private final ConsultarTipoUsuario consultarTipoUsuario;

    private final RemoverTipoUsuario removerTipoUsuario;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TipoUsuarioDTO criarTipoUsuario(@RequestBody @Validated final TipoUsuarioDTO tipoUsuarioDTO) {
        return toDTO(criarTipoUsuario.execute(toDomain(tipoUsuarioDTO)));
    }

    @PutMapping("/{id}")
    public TipoUsuarioDTO alterarTipoUsuario(@RequestBody @Validated final TipoUsuarioDTO tipoUsuarioDTO,
                                     @PathVariable final Long id) {

        return toDTO(alterarTipoUsuario.execute(id, toDomain(tipoUsuarioDTO)));
    }

    @GetMapping
    public List<TipoUsuarioDTO> listarTiposUsuario(final PaginaDTO paginaDTO) {
        return listarTiposUsuario.execute(paginaDTO.getPagina(), paginaDTO.getTamanhoPagina())
                .stream()
                .map(TipoUsuarioDTOMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public TipoUsuarioDTO consultarTipoUsuario(@PathVariable final Long id) {
        return toDTO(consultarTipoUsuario.execute(id));
    }

    @DeleteMapping("/{id}")
    public void removerTipoUsuario(@PathVariable final Long id) {
        removerTipoUsuario.execute(id);
    }
}
