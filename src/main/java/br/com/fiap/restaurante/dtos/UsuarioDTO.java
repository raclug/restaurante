package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.enums.TipoUsuarioEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @JsonProperty(value = "id", access = READ_ONLY)
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("email")
    private String email;

    @JsonProperty("login")
    private String login;

    @JsonProperty(value = "senha", access = WRITE_ONLY)
    private String senha;

    @JsonProperty("endereco")
    private EnderecoDTO endereco;

    @JsonProperty("tipo_usuario")
    private TipoUsuarioEnum tipoUsuario;
}
