package br.com.fiap.restaurante.domain.entities;


public record Usuario(Long id,
                      String nome,
                      String email,
                      String login,
                      String senha,
                      TipoUsuarioEnum tipoUsuario,
                      Endereco endereco) {
}
