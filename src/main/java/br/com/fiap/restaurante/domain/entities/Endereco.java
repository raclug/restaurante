package br.com.fiap.restaurante.domain.entities;

import br.com.fiap.restaurante.enums.UfEnum;

public record Endereco(String logradouro,
                       String numero,
                       String complemento,
                       String bairro,
                       String cidade,
                       UfEnum estado,
                       String cep) {
}
