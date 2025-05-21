package br.com.fiap.restaurante.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @JsonProperty(value = "senha", access = WRITE_ONLY)
    private String senha;

    @JsonProperty(value = "login", access = WRITE_ONLY)
    private String login;
}
