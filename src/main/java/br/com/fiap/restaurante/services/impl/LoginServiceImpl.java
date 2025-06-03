package br.com.fiap.restaurante.services.impl;

import br.com.fiap.restaurante.dtos.LoginDTO;
import br.com.fiap.restaurante.exceptions.NaoAutorizadoException;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import br.com.fiap.restaurante.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void validarLogin(final LoginDTO loginDTO) {

        var usuarioEntity = usuarioRepository.findFirstByLogin(loginDTO.getLogin()).orElseThrow(() ->
                new NaoAutorizadoException("Login ou senha inválidos")
        );

        var senhaCriptografada = usuarioEntity.getSenha().getSenha();

        if (!passwordEncoder.matches(loginDTO.getSenha(), senhaCriptografada)) {
            throw new NaoAutorizadoException("Login ou senha inválidos");
        }
    }
}
