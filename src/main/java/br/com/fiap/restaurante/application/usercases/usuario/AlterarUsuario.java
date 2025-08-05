package br.com.fiap.restaurante.application.usercases.usuario;

import br.com.fiap.restaurante.domain.entities.Usuario;

public interface AlterarUsuario {

    Usuario execute(Long id, Usuario usuario);
}
