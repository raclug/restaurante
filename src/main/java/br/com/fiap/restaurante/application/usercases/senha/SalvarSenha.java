package br.com.fiap.restaurante.application.usercases.senha;

public interface SalvarSenha {

    void execute(Long usuarioId, String senhaAtual, String novaSenha);
}
