package br.com.fiap.restaurante.application.usercases.usuario;

public interface AdicionarTipoUsuario {

    void execute(Long idUsuario, Long idTipoUsuario);
}
