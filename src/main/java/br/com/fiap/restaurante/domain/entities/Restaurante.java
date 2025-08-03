package br.com.fiap.restaurante.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurante {

    private Long id;
    private String nome;
    private Endereco endereco;
    private String tipoCozinha;
    private String horarioAbertura;
    private String horarioFechamento;
    private Long idResponsavel;
}
