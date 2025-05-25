package br.com.fiap.restaurante.services;

import br.com.fiap.restaurante.dtos.SenhaDTO;

public interface SenhaService {

    void alterarSenha(final Long usuarioId, final SenhaDTO senhaDTO);
}
