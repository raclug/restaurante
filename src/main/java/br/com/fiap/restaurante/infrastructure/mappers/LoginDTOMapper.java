package br.com.fiap.restaurante.infrastructure.mappers;

import br.com.fiap.restaurante.domain.entities.Login;
import br.com.fiap.restaurante.infrastructure.dtos.LoginDTO;

public class LoginDTOMapper {

    private LoginDTOMapper() {}

    public static Login toDomain(final LoginDTO loginDTO) {
        return new Login(
                loginDTO.getSenha(),
                loginDTO.getLogin()
        );
    }
}
