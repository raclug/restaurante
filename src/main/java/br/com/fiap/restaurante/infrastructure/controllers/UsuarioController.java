package br.com.fiap.restaurante.infrastructure.controllers;


import br.com.fiap.restaurante.infrastructure.controllers.interfaces.IUsuarioController;
import br.com.fiap.restaurante.infrastructure.dtos.AlteracaoUsuarioDTO;
import br.com.fiap.restaurante.infrastructure.dtos.PaginaDTO;
import br.com.fiap.restaurante.infrastructure.dtos.UsuarioDTO;
import br.com.fiap.restaurante.infrastructure.mappers.AlteracaoUsuarioDTOMapper;
import br.com.fiap.restaurante.infrastructure.mappers.UsuarioDTOMapper;
import br.com.fiap.restaurante.infrastructure.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.fiap.restaurante.infrastructure.mappers.UsuarioDTOMapper.toDTO;
import static br.com.fiap.restaurante.infrastructure.mappers.UsuarioDTOMapper.toDomain;

@RestController
@RequestMapping("/v1/usuarios")
@AllArgsConstructor
public class UsuarioController implements IUsuarioController {

    private final UsuarioService usuarioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UsuarioDTO criarUsuario(@RequestBody @Validated final UsuarioDTO usuarioDTO) {
        return toDTO(usuarioService.criarUsuario(toDomain(usuarioDTO)));
    }

    @PutMapping("/{id}")
    public UsuarioDTO alterarUsuario(@RequestBody @Validated final AlteracaoUsuarioDTO usuarioDTO,
                                     @PathVariable final Long id) {

        return toDTO(usuarioService.alterarUsario(id, AlteracaoUsuarioDTOMapper.toDomain(usuarioDTO)));
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarios(final PaginaDTO paginaDTO) {
        return usuarioService.listarUsuarios(paginaDTO.getPagina(), paginaDTO.getTamanhoPagina())
                .stream()
                .map(UsuarioDTOMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public UsuarioDTO consultarUsuario(@PathVariable final Long id) {
        return toDTO(usuarioService.consultarUsuario(id));
    }

    @DeleteMapping("/{id}")
    public void removerUsuario(@PathVariable final Long id) {
        usuarioService.removerUsuario(id);
    }

    @PostMapping("/{usuarioId}/tipos-usuario/{tipoUsuarioId}")
    public void adicionarTipoUsuario(Long usuarioId, Long tipoUsuarioId) {
        usuarioService.adicionarTipoUsuario(usuarioId, tipoUsuarioId);
    }
}
