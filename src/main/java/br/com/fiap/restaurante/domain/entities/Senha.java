package br.com.fiap.restaurante.domain.entities;

public record Senha (
        Long id,
        Long usuarioId,
        String senha) {
}
