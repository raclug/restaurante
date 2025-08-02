package br.com.fiap.restaurante.application.usercases.usuario;

import br.com.fiap.restaurante.domain.entities.Usuario;

import java.util.List;

public interface ListarUsuarios {

    List<Usuario> listarUsuarios(Integer pagina,
                                 Integer tamanhoPagina);
}
