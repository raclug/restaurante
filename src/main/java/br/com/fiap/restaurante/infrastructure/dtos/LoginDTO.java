package br.com.fiap.restaurante.infrastructure.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para autenticação do usuário")
public class LoginDTO {

    @NotBlank(message = "A senha é obrigatória.")
    @JsonProperty(access = WRITE_ONLY)
    @Schema(description = "Senha do usuário", example = "minhaSenha123", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String senha;

    @NotBlank(message = "O login é obrigatório.")
    @JsonProperty(access = WRITE_ONLY)
    @Schema(description = "Login do usuário", example = "usuario@email.com", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String login;
}
