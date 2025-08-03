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

        var enderecoParaAlteracao = new Endereco(
                usuarioConsulta.get().getEndereco().id(),
                usuario.getEndereco().logradouro(),
                usuario.getEndereco().numero(),
                usuario.getEndereco().complemento(),
                usuario.getEndereco().bairro(),
                usuario.getEndereco().cidade(),
                usuario.getEndereco().estado(),
                usuario.getEndereco().cep()
        );

        var usuarioParaAlteracao = new Usuario(
                id,
                usuario.getNome(),
                usuario.getEmail(),
                usuarioConsulta.get().getLogin(),
                usuarioConsulta.get().getSenha(),
                usuarioConsulta.get().getTipoUsuario(),
                enderecoParaAlteracao);

        return usuarioPort.salvarUsuario(usuarioParaAlteracao);
    }
}
