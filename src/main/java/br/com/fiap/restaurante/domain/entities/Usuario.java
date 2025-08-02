package br.com.fiap.restaurante.domain.entities;

import br.com.fiap.restaurante.enums.TipoUsuarioEnum;

public record Usuario(Long id,
                      String nome,
                      String email,
                      String login,
                      String senha,
                      TipoUsuarioEnum tipoUsuario,
                      Endereco endereco) {
}
