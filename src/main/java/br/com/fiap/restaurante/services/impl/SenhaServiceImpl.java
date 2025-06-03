package br.com.fiap.restaurante.services.impl;

import br.com.fiap.restaurante.dtos.SenhaDTO;
import br.com.fiap.restaurante.exceptions.NaoAutorizadoException;
import br.com.fiap.restaurante.exceptions.UsuarioNaoEncontradoException;
import br.com.fiap.restaurante.repositories.SenhaRepository;
import br.com.fiap.restaurante.services.SenhaService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SenhaServiceImpl implements SenhaService {

    private final SenhaRepository senhaRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void alterarSenha(final Long usuarioId, final SenhaDTO senhaDTO) {

        var senhaEntity = senhaRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado."));

        if (!passwordEncoder.matches(senhaDTO.getSenha(), senhaEntity.getSenha())) {
            throw new NaoAutorizadoException("Senha atual inválida");
        }

        senhaEntity.setSenha(passwordEncoder.encode(senhaDTO.getNovaSenha()));

        senhaRepository.save(senhaEntity);
    }
}
