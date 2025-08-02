package br.com.fiap.restaurante.application.usercases.usuario;

import br.com.fiap.restaurante.domain.entities.Usuario;

import java.util.List;

public interface ListarUsuarios {

    List<Usuario> execute(Integer pagina, Integer tamanhoPagina);
}
