package br.com.fiap.restaurante.infrastructure.controllers;


import br.com.fiap.restaurante.application.usercases.usuario.*;
import br.com.fiap.restaurante.infrastructure.controllers.interfaces.IUsuarioController;
import br.com.fiap.restaurante.infrastructure.dtos.AlteracaoUsuarioDTO;
import br.com.fiap.restaurante.infrastructure.dtos.PaginaDTO;
import br.com.fiap.restaurante.infrastructure.dtos.UsuarioDTO;
import br.com.fiap.restaurante.infrastructure.mappers.AlteracaoUsuarioDTOMapper;
import br.com.fiap.restaurante.infrastructure.mappers.UsuarioDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    private final CriarUsuario criarUsuario;

    private final AlterarUsuario alterarUsuario;

    private final ListarUsuarios listarUsuarios;

    private final ConsultarUsuario consultarUsuario;

    private final RemoverUsuario removerUsuario;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UsuarioDTO criarUsuario(@RequestBody @Validated final UsuarioDTO usuarioDTO) {
        return toDTO(criarUsuario.execute(toDomain(usuarioDTO)));
    }

    @PutMapping("/{id}")
    public UsuarioDTO alterarUsuario(@RequestBody @Validated final AlteracaoUsuarioDTO usuarioDTO,
                                     @PathVariable final Long id) {

        return toDTO(alterarUsuario.execute(id, AlteracaoUsuarioDTOMapper.toDomain(usuarioDTO)));
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarios(final PaginaDTO paginaDTO) {
        return listarUsuarios.execute(paginaDTO.getPagina(), paginaDTO.getTamanhoPagina())
                .stream()
                .map(UsuarioDTOMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public UsuarioDTO consultarUsuario(@PathVariable final Long id) {
        return toDTO(consultarUsuario.execute(id));
    }

    @DeleteMapping("/{id}")
    public void removerUsuario(@PathVariable final Long id) {
        removerUsuario.execute(id);
    }
}
