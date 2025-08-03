package br.com.fiap.restaurante.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TipoUsuario {

    private Long id;

    private String nome;
}
