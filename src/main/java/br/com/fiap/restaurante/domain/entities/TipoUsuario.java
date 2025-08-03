package br.com.fiap.restaurante.domain.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoUsuario {

    private Long id;

    private String nome;
}
