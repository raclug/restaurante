package br.com.fiap.restaurante.infrastructure.services;

import br.com.fiap.restaurante.application.usercases.usuario.*;
import br.com.fiap.restaurante.domain.entities.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final CriarUsuario criarUsuario;

    private final AlterarUsuario alterarUsuario;

    private final ListarUsuarios listarUsuarios;

    private final ConsultarUsuario consultarUsuario;

    private final RemoverUsuario removerUsuario;

    private final AdicionarTipoUsuario adicionarTipoUsuario;

    @Transactional
    public Usuario criarUsuario(final Usuario usuario) {
        return criarUsuario.execute(usuario);
    }

    @Transactional
    public Usuario alterarUsario(final Long usuarioId, final Usuario usuario) {
        return alterarUsuario.execute(usuarioId, usuario);
    }

    public List<Usuario> listarUsuarios(final Integer pagina, final Integer tamanhoPagina) {
        return listarUsuarios.execute(pagina, tamanhoPagina);
    }

    public Usuario consultarUsuario(final Long usuarioId) {
        return consultarUsuario.execute(usuarioId);
    }

    public void removerUsuario(final Long usuarioId) {
        removerUsuario.execute(usuarioId);
    }

    @Transactional
    public void adicionarTipoUsuario(final Long usuarioId, final Long tipoUsuarioId) {
        adicionarTipoUsuario.execute(usuarioId, tipoUsuarioId);
    }
}
