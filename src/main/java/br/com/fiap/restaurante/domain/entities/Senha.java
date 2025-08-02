package br.com.fiap.restaurante.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Senha {
    private Long id;
    private Long usuarioId;
    private String senha;
}
