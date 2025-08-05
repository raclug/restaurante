package br.com.fiap.restaurante.application.usercases.usuario.impl;

import br.com.fiap.restaurante.application.ports.UsuarioPort;
import br.com.fiap.restaurante.application.usercases.usuario.AlterarUsuario;
import br.com.fiap.restaurante.domain.entities.Endereco;
import br.com.fiap.restaurante.domain.entities.Usuario;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlterarUsuarioImpl implements AlterarUsuario {

    private final UsuarioPort usuarioPort;

    @Override
    public Usuario execute(final Long id, final Usuario usuario) {

        var usuarioConsulta = usuarioPort.consultarUsuarioPorId(id);

        if (usuarioConsulta.isEmpty()) {
            throw new RegistroNaoEncontradoException("Usuário não encontrado.");
        }

        var enderecoParaAlteracao = getEndereco(usuario, usuarioConsulta.get());

        var usuarioParaAlteracao = getUsuario(id, usuario, usuarioConsulta.get(), enderecoParaAlteracao);

        return usuarioPort.salvarUsuario(usuarioParaAlteracao);
    }

    private static Usuario getUsuario(final Long id,
                                      final Usuario usuario,
                                      final Usuario usuarioConsulta,
                                      final Endereco enderecoParaAlteracao) {

        return new Usuario(
                id,
                usuario.getNome(),
                usuario.getEmail(),
                usuarioConsulta.getLogin(),
                usuarioConsulta.getSenha(),
                usuarioConsulta.getTipoUsuario(),
                enderecoParaAlteracao);
    }

    private static Endereco getEndereco(final Usuario usuario,
                                        final Usuario usuarioConsulta) {

        return new Endereco(
                usuarioConsulta.getEndereco().id(),
                usuario.getEndereco().logradouro(),
                usuario.getEndereco().numero(),
                usuario.getEndereco().complemento(),
                usuario.getEndereco().bairro(),
                usuario.getEndereco().cidade(),
                usuario.getEndereco().estado(),
                usuario.getEndereco().cep()
        );
    }
}
