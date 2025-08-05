package br.com.fiap.restaurante.application.ports;

import br.com.fiap.restaurante.domain.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioPort {

    Usuario salvarUsuario(Usuario usuario);

    void removerUsuario(Long id);

    List<Usuario> listarUsuarios(Integer pagina, Integer tamanhoPagina);

    Optional<Usuario> consultarUsuarioPorId(Long id);

    Optional<Usuario> consultarUsuarioPorLogin(String login);

    void adicionarTipoUsuario(Long idUsuario, Long idTipoUsuario);
}
