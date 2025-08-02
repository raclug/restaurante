package br.com.fiap.restaurante.infrastructure.controllers;

import br.com.fiap.restaurante.dtos.AlteracaoUsuarioDTO;
import br.com.fiap.restaurante.dtos.UsuarioDTO;
import br.com.fiap.restaurante.infrastructure.controllers.interfaces.IUsuarioController;
import br.com.fiap.restaurante.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
@AllArgsConstructor
public class UsuarioController implements IUsuarioController {

    private final UsuarioService usuarioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UsuarioDTO criarUsuario(@RequestBody @Validated final UsuarioDTO usuarioDTO) {
        return usuarioService.criarUsuario(usuarioDTO);
    }

    @PutMapping("/{id}")
    public UsuarioDTO alterarUsuario(@RequestBody @Validated final AlteracaoUsuarioDTO usuarioDTO,
                                     @PathVariable final Long id) {

        return usuarioService.alterarUsuario(id, usuarioDTO);
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarios(final Pageable pageable) {
        return usuarioService.listarUsuarios(pageable);
    }

    @GetMapping("/{id}")
    public UsuarioDTO consultarUsuario(@PathVariable final Long id) {
        return usuarioService.consultarUsuario(id);
    }

    @DeleteMapping("/{id}")
    public void removerUsuario(@PathVariable final Long id) {
        usuarioService.removerUsuario(id);
    }
}
