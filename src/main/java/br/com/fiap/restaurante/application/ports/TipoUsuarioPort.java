package br.com.fiap.restaurante.application.ports;

import br.com.fiap.restaurante.domain.entities.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface TipoUsuarioPort {

    TipoUsuario salvarTipoUsuario(TipoUsuario tipoUsuario);

    void removerTipoUsuario(Long id);

    Optional<TipoUsuario> consultarTipoUsuarioPorId(Long id);

    List<TipoUsuario> listarTiposUsuario(Integer pagina, Integer tamanhoPagina);
}
