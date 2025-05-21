package br.com.fiap.restaurante.controllers;

import br.com.fiap.restaurante.dtos.LoginDTO;
import br.com.fiap.restaurante.dtos.SenhaDTO;
import br.com.fiap.restaurante.dtos.UsuarioDTO;
import br.com.fiap.restaurante.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UsuarioDTO criarUsuario(@RequestBody final UsuarioDTO usuarioDTO) {
        return usuarioService.criarUsuario(usuarioDTO);
    }

    @PutMapping("/{id}")
    public UsuarioDTO alterarUsuario(@RequestBody final UsuarioDTO usuarioDTO, @PathVariable final Long id) {
        return usuarioService.alterarUsuario(id, usuarioDTO);
    }

    @PutMapping("/{id}/senhas")
    public void alterarSenha(@RequestBody final SenhaDTO senhaDTO, @PathVariable final Long id) {
        usuarioService.alterarSenha(id, senhaDTO);
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioDTO consultarUsuario(@PathVariable final Long id) {
        return usuarioService.consultarUsuario(id);
    }

    @DeleteMapping("/{id}")
    public void removerUsuario(@PathVariable final Long id) {
        usuarioService.removerUsuario(id);
    }

    @PostMapping("/login")
    public void validarLogin(@RequestBody final LoginDTO loginDTO) {
        usuarioService.validarLogin(loginDTO);
    }
}
