package br.com.fiap.restaurante.services;

import br.com.fiap.restaurante.dtos.LoginDTO;

public interface LoginService {

    void validarLogin(final LoginDTO loginDTO);
}
