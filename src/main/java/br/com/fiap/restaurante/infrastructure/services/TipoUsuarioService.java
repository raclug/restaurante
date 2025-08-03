package br.com.fiap.restaurante.infrastructure.services;

import br.com.fiap.restaurante.application.usercases.tipousuario.*;
import br.com.fiap.restaurante.domain.entities.TipoUsuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoUsuarioService {

    private final CriarTipoUsuario criarTipoUsuario;

    private final AlterarTipoUsuario alterarTipoUsuario;

    private final ListarTiposUsuario listarTiposUsuario;

    private final ConsultarTipoUsuario consultarTipoUsuario;

    private final RemoverTipoUsuario removerTipoUsuario;

    @Transactional
    public TipoUsuario criarTipoUsuario(final TipoUsuario tipoUsuario) {
        return criarTipoUsuario.execute(tipoUsuario);
    }

    @Transactional
    public TipoUsuario alterarTipoUsuario(final Long tipoUsuarioId, final TipoUsuario tipoUsuario) {
        return alterarTipoUsuario.execute(tipoUsuarioId, tipoUsuario);
    }

    public TipoUsuario consultarTipoUsuario(final Long tipoUsuarioId) {
        return consultarTipoUsuario.execute(tipoUsuarioId);
    }

    @Transactional
    public void removerTipoUsuario(final Long tipoUsuarioId) {
        removerTipoUsuario.execute(tipoUsuarioId);
    }

    public List<TipoUsuario> listarTiposUsuario(final Integer pagina, final Integer tamanhoPagina) {
        return listarTiposUsuario.execute(pagina, tamanhoPagina);
    }
}
