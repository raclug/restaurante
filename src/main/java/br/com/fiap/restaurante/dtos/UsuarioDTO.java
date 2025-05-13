package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.enums.TipoUsuarioEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

public record UsuarioDTO(
        @JsonProperty(value = "id", access = READ_ONLY)
        Long id,
        @JsonProperty("nome")
        String nome,
        @JsonProperty("email")
        String email,
        @JsonProperty("login")
        String login,
        @JsonProperty("endereco")
        String endereco,
        @JsonProperty("tipo_usuario")
        TipoUsuarioEnum tipoUsuario) {

}
