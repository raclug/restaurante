package br.com.fiap.restaurante.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SenhaDTO {

    @JsonProperty(value = "senha", access = WRITE_ONLY)
    private String senha;

    @JsonProperty(value = "confirmacao_senha", access = WRITE_ONLY)
    private String confirmacaoSenha;

    @JsonProperty(value = "nova_senha", access = WRITE_ONLY)
    private String novaSenha;
}
