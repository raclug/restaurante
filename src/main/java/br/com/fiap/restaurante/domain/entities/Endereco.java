package br.com.fiap.restaurante.domain.entities;


public record Endereco(Long id,
                       String logradouro,
                       String numero,
                       String complemento,
                       String bairro,
                       String cidade,
                       UfEnum estado,
                       String cep) {
}
