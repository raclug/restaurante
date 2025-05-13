package br.com.fiap.restaurante.controllers;

import br.com.fiap.restaurante.dtos.UsuarioDTO;
import br.com.fiap.restaurante.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @PostMapping
    public void salvarUsuario(@RequestBody final UsuarioDTO usuarioDTO) {
        usuarioService.salvarUsuario(usuarioDTO);
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioDTO listarUsuarios(@PathVariable Long id) {
        return usuarioService.consultarUsuario(id);
    }
}
