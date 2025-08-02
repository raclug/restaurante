package br.com.fiap.restaurante.infrastructure.gateways;

import br.com.fiap.restaurante.application.ports.SenhaPort;
import br.com.fiap.restaurante.domain.entities.Senha;
import br.com.fiap.restaurante.infrastructure.mappers.SenhaEntityMapper;
import br.com.fiap.restaurante.infrastructure.persistence.repositories.SenhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.fiap.restaurante.infrastructure.mappers.SenhaEntityMapper.toEntity;

@Component
@AllArgsConstructor
public class SenhaRepositoryGateway implements SenhaPort {

    private final SenhaRepository senhaRepository;

    @Override
    public Optional<Senha> consultarSenhaPorUsuarioId(Long usuarioId) {
        return senhaRepository.findByUsuarioId(usuarioId)
                .map(SenhaEntityMapper::toDomain);
    }

    @Override
    public Optional<Senha> consultarSenhaPorLogin(String login) {
        return Optional.empty();
    }

    @Override
    public void salvarSenha(Senha senha) {
        senhaRepository.save(toEntity(senha));
    }
}
