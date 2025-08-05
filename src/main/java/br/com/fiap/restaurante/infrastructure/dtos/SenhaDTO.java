package br.com.fiap.restaurante.infrastructure.dtos;

import br.com.fiap.restaurante.validations.SenhasIguais;
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
@SenhasIguais
@Schema(description = "DTO para alteração de senha do usuário")
public class SenhaDTO {

    @NotBlank(message = "A senha é obrigatória.")
    @JsonProperty(access = WRITE_ONLY)
    @Schema(description = "Senha atual do usuário", example = "senhaAtual123", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String senha;

    @NotBlank(message = "A senha confirmação é obrigatória.")
    @JsonProperty(access = WRITE_ONLY)
    @Schema(description = "Confirmação da nova senha", example = "novaSenha123", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String confirmacaoSenha;

    @NotBlank(message = "A nova senha é obrigatória.")
    @JsonProperty(access = WRITE_ONLY)
    @Schema(description = "Nova senha do usuário", example = "novaSenha123", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String novaSenha;
}
