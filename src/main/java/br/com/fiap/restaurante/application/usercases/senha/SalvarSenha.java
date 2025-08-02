package br.com.fiap.restaurante.application.usercases.senha;

public interface SalvarSenha {

    void salvarSenha(Long usuarioId, String senhaAtual, String novaSenha);
}
