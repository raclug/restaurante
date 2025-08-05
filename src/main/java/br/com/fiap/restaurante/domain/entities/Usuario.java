package br.com.fiap.restaurante.domain.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private Senha senha;
    private TipoUsuario tipoUsuario;
    private Endereco endereco;
}
