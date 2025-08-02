package br.com.fiap.restaurante.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private Senha senha;
    private TipoUsuarioEnum tipoUsuario;
    private Endereco endereco;
}
