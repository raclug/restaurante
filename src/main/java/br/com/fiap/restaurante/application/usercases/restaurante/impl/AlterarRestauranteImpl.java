package br.com.fiap.restaurante.application.usercases.restaurante.impl;

import br.com.fiap.restaurante.application.ports.RestaurantePort;
import br.com.fiap.restaurante.application.ports.TipoUsuarioPort;
import br.com.fiap.restaurante.application.usercases.restaurante.AlterarRestaurante;
import br.com.fiap.restaurante.domain.entities.Endereco;
import br.com.fiap.restaurante.domain.entities.Restaurante;
import br.com.fiap.restaurante.domain.exceptions.RegistroNaoEncontradoException;
import br.com.fiap.restaurante.domain.exceptions.TipoUsuarioException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlterarRestauranteImpl implements AlterarRestaurante {

    private final RestaurantePort restaurantePort;

    private final TipoUsuarioPort tipoUsuarioPort;

    @Override
    public Restaurante execute(final Long id, final Restaurante restaurante) {

        var restauranteConsulta = restaurantePort.consultarRestaurantePorId(id);

        if (restauranteConsulta.isEmpty()) {
            throw new RegistroNaoEncontradoException("Restaurante não encontrado.");
        }

        var tipoUsuario = tipoUsuarioPort.consultarTipoUsuarioPorId(
                        restaurante.getResponsavel().getTipoUsuario().getId())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Tipo de usuário não encontrado."));

        if (!"Dono de Restaurante".equals(tipoUsuario.getNome())) {
            throw new TipoUsuarioException(
                    "Somente usuário do tipo 'Dono de Restaurante' pode ser responsável do restaurante.");
        }

        var enderecoParaAlteracao = getEndereco(restaurante, restauranteConsulta.get());

        var restauranteParaAlteracao = getRestaurante(id, restaurante, enderecoParaAlteracao);

        return restaurantePort.salvarRestaurante(restauranteParaAlteracao);
    }

    private Endereco getEndereco(final Restaurante restaurante, final Restaurante restauranteConsulta) {

        return new Endereco(
                restauranteConsulta.getEndereco().id(),
                restaurante.getEndereco().logradouro(),
                restaurante.getEndereco().numero(),
                restaurante.getEndereco().complemento(),
                restaurante.getEndereco().bairro(),
                restaurante.getEndereco().cidade(),
                restaurante.getEndereco().estado(),
                restaurante.getEndereco().cep()
        );
    }

    private Restaurante getRestaurante(final Long id,
                                       final Restaurante restaurante,
                                       final Endereco enderecoParaAlteracao) {

        return new Restaurante(
                id,
                restaurante.getNome(),
                enderecoParaAlteracao,
                restaurante.getTipoCozinha(),
                restaurante.getHorarioAbertura(),
                restaurante.getHorarioFechamento(),
                restaurante.getResponsavel()
        );
    }
}
